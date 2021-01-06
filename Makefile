default: clean
	sbt 'runMain randmod.RandomModuleTest'

MemrdTest: clean
	sbt 'runMain randmod.MemrdTest'

RandomModuleFromFile: clean
	sbt 'runMain randmod.RandomModuleFromFile sequence.txt'

AutoRandomModule: clean_seq
	python3 sequence_gen.py
	$(MAKE) RandomModuleFromFile

clean:
	rm -rf test_run_dir
	rm -rf build

clean_seq:
	rm -f sequence.txt
