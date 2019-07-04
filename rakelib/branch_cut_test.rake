require 'branch_cut'

namespace :release_branch do |_args|
  desc 'Create and push release branch'
  task :create_and_push do
    BranchCut.cut_and_push
  end
end
