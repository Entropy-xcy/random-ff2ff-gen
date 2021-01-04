default: clean
	sbt 'runMain randmod.RandomModuleTest'

MemrdTest: clean
	sbt 'runMain randmod.MemrdTest'

clean:
	rm -rf test_run_dir
