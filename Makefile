all:
	+$(MAKE) -C Day1
	+$(MAKE) -C Day2

clean:
	rm -rf */*.class