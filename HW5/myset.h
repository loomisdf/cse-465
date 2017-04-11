#ifndef _MYSET_
#define _MYSET_

#include <cstring>
#include <iostream>
#include <initializer_list>

#pragma warning(disable: 4996)

const bool DUMP = true;

class myset {
public:
	static const int MIN_CAPACITY = 10;

	int *data;	// must be dynamically allocate 1D array
	myset() {
		data = new int [MIN_CAPACITY];	
	}
	~myset() {
	}
	myset(const myset &other) {
	}
	myset &operator = (const myset &other) {
		return *this;
	}
	myset(myset &&other) {
	}
	myset &operator = (myset &&other) 	{
		return *this;
	}
	// returns the number of elements in the set.
	int size() const {
		return 0;
	}
	// Returns the current size of the array.
	int capacity() const {
		return 0;
	}
	// Removes a value to the set. Return true iff the element
	// was successfully removed.
	bool remove(int value) {
		return false;
	}
	// Adds a value to the set. Return true iff the element
	// was successfully added.
	bool insert(int value) {
		return false;
	}
	// Returns true if value is part of set.
	bool isElement(int value) const {
		return false;
	}
	// Returns an array containing the elements in the set.
	// This array is dynamically allocated an must be deleted by
	// the caller.
	int *getValues(int &sz) const {
		sz = 5;
		int *result = new int[sz];
		result[0] = 5;
		result[1] = 15;
		result[2] = 20;
		result[3] = 30;
		result[4] = 40;
		return result;
	}
};

#endif
