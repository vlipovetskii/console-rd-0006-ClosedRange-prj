rem How to run Gradle test when all tests are UP-TO-DATE? https://stackoverflow.com/questions/29427020/how-to-run-gradle-test-when-all-tests-are-up-to-date

rem One option would be using the --rerun-tasks flag in the command line. This would rerun all the the test task and all the tasks it depends on.
rem If you're only interested in rerunning the tests then another option would be to make gradle clean the tests results before executing the tests. This can be done using the cleanTest task
rem Therefore, all you need in order to re-run your tests is to also run the cleanTest task, i.e.:
rem gradle cleanTest test

rem unning cleanTest before test will not rerun the tests, it cleans their output, but the test task will still get the test results from the cache - see github.com/gradle/gradle/issues/9153

rem --rerun-tasks works, but is inefficient as it reruns all tasks.
rem cleanTest by itself may not suffice due to build cache.
rem so, best way to accomplish this is:
rem ./gradlew --no-build-cache cleanTest test

rem just clean build\reports\, build\test-results\
gradlew cleanTest
