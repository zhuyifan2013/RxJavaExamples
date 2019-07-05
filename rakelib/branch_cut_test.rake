require 'branch_cut'
require 'merge_commit'

namespace :release_branch do |_args|
  desc 'Create and push release branch'
  task :create_and_push do
    BranchCut.cut_and_push
  end

  task :latest_commit do
    Commit.latest_commit
  end
end
