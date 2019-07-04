require 'active_support/duration'
require 'octokit'
require_relative 'sheriff_utils'

# Used to cut a release branch weekly from CI
class BranchCut
  def self.cut_and_push
    puts 'Fetching latest master from origin'

    unless system('git fetch origin master')
      abort('Unable to fetch latest from origin')
    end

    time = Time.new

    branch_name = "yifan_test_auto_#{time.yday}_#{time.hour}_#{time.min}"


    # cut branch
    puts "Cutting branch #{branch_name} from master"
    `git checkout -b #{branch_name} origin/master`

    # push branch to origin
    puts "Pushing branch #{branch_name} to origin"
    unless system("git push -u origin #{branch_name}")
      abort('Unable to push branch to origin')
    end

    # wait a bit as sometimes this API call fails if called too quickly after pushing the branch to origin
    puts 'Waiting a bit before protecting branch...'
    sleep 6

    # mark branch as protected
    puts "Marking branch #{branch_name} as protected via Github API"
    
    client = Octokit::Client.new(:login => 'zhuyifan2013', :password=> '1993xiaofan')

    options = {}
    # protect for admins
    options[:enforce_admins] = false
    # require branches to be up to date before merging
    options[:strict] = true
    # no need for additional status checks
    options[:contexts] = []
    # don't force required pull request reviews
    options[:required_pull_request_reviews] = nil
    client.protect_branch('zhuyifan2013/RxJavaExamples', branch_name, options)
  end
end
