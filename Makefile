default: clean
	sbt 'runMain randmod.RandomPathTest'


clean:
	rm -rf test_run_dir
