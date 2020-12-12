all:
	+$(MAKE) -C Day1
	+$(MAKE) -C Day2
	+$(MAKE) -C Day3

clean:
	rm -rf */*.class