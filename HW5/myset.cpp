#include <iostream>
#include "myset.h"

void printValues(myset* set) {
	int size;
	int* values = set->getValues(size);

	std::cout << "Values: [ ";
	for(int i = 0; i < set->size(); i++) {
		std::cout << values[i] << " ";
	}
	std::cout << "]" << std::endl;
}

int main(int argc, char *argv[]) {
	myset set;
	for(int i = 0; i < 10; i++) {
		set.insert(i);
	}
	set.insert(10);

	std::cout << "Size: " << set.size() << std::endl;
	std::cout << "Capacity: " << set.capacity() << std::endl;
	printValues(&set);

	std::cout << "insert(5): " << set.insert(5) << std::endl;
	std::cout << "insert(20): " << set.insert(20) << std::endl;
	printValues(&set);
	std::cout << "Size: " << set.size() << std::endl;
	std::cout << "remove(5): " << set.remove(5) << std::endl;
	printValues(&set);
	std::cout << "Size: " << set.size() << std::endl;

	std::cout << "\ncopied set:" << std::endl;
	myset set_copy(set);
	printValues(&set_copy);

	std::cout << "&set: " << &set << std::endl;
	std::cout << "&set_copy: " << &set_copy << std::endl;
	return 0;
}
