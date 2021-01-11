all:
	+$(MAKE) -C Day1
	+$(MAKE) -C Day2
	+$(MAKE) -C Day3
	# +$(MAKE) -C Day4
	# +$(MAKE) -C Day5
	+$(MAKE) -C Day6
	+$(MAKE) -C Day8

clean:
	rm -rf */*.class