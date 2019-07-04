require 'active_support'
require 'active_support/core_ext'

# Shared utilities for sheriff related rake tasks
class SheriffUtils
  attr_reader :version_name
  attr_reader :release_week

  RELEASE_DAY_OF_WEEK = 2 # Tuesday

  def initialize(now = Date.today)
    @now = now
    # This is used to name the newly cut branch
    @version_name = @release_week = version_name_as_of(@now)
    # These are valid after the branch is already cut
  end

  def version_name_as_of(date_time)
    # Day of week (0-6, Sunday is zero)
    today_day_of_week = date_time.to_date.wday
    days_for_release =
      if today_day_of_week < RELEASE_DAY_OF_WEEK
        RELEASE_DAY_OF_WEEK - today_day_of_week
      else
        7 - (today_day_of_week - RELEASE_DAY_OF_WEEK)
      end
    days_for_release.days.since(date_time).strftime('%y.%V')
  end
end
