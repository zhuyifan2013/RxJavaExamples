require 'active_support/duration'
require 'octokit'
require_relative 'sheriff_utils'

# Used to cut a release branch weekly from CI
class Commit
  def self.latest_commit
  	client = Octokit::Client.new(:login => 'zhuyifan2013', :password=> '1993xiaofan')
  	options = {}
    
    options[:sha] = 'yifan_test_release'

  	list = client.commits('zhuyifan2013/RxJavaExamples', options)
  	puts list.first.sha

  	result = client.merge('zhuyifan2013/RxJavaExamples', 'temp', list.first.sha, {:commit_message => list.first.commit.message})
  	puts result.sha

  end
end