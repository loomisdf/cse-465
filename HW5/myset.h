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

	int *data;		// must be dynamically allocate 1D array
	int _size; 		// # of items in the set
	int _capacity; 	// storage capacity of the set

	myset() {
		data = new int [MIN_CAPACITY];
		_capacity = MIN_CAPACITY;
		_size = 0;
	}

	~myset() {
		delete []data;
	}

	myset(const myset &other) {
		_capacity = other.capacity();
		_size = other.size();
		data = new int[_capacity];

		memcpy(data, other.data, _capacity * sizeof(int));
	}

	myset &operator = (const myset &other) {
		if(this != &other) {
			delete []data;
			_capacity = other.capacity();
			_size = other.size();
			data = new int[_capacity];
			memcpy(data, other.data, _capacity * sizeof(int));
		}
		return *this;
	}

	myset(myset &&other) {
		data = other.data;
		_size = other._size;
		_capacity = other._capacity;

		other.data = nullptr;
		other._size = 0;
		other._capacity = MIN_CAPACITY;
	}

	myset &operator = (myset &&other) 	{
		if(this != &other) {
			delete []data;
			data = other.data;
			_capacity = other._capacity;
			_size = other._size;

			other.data = nullptr;
			other._size = 0;
			other._capacity = MIN_CAPACITY;
		}
		return *this;
	}

	// returns the number of elements in the set.
	int size() const {
		return _size;
	}

	// Returns the current size of the array.
	int capacity() const {
		return _capacity;
	}

	// Removes a value to the set. Return true iff the element
	// was successfully removed.
	bool remove(int value) {
		int loc = -1; // location of element to remove
		for(int i = 0; i < _size; i++) {
			if(data[i] == value) {
				loc = i;
				break;
			}
		}
		if(loc > -1) {
			// shift over elements in array
			for(int i = loc; i < _size - 1; i++) {
				data[i] = data[i+1];
			}
			_size--;
			return true;
		} else {
			return false;
		}
	}

	// Adds a value to the set. Return true iff the element
	// was successfully added.
	bool insert(int value) {
		if(_size == 0) {
			data[0] = value;
			_size++;
			return true;
		}
		else {
			// Check to see if the set already contains the element
			if(isElement(value)) {
				return false;
			}

			// Resize the array if it's full
			if(_size == _capacity) {
				// resize the array
				int new_capacity = _capacity + (_capacity / 2);
				int* newData = new int[new_capacity];

				memcpy(newData, data, _capacity * sizeof(int));

				delete []data; // Free memory
				data = newData;
				_capacity = new_capacity;
			}

			// Insert new element
			data[_size] = value;
			_size++;
			return true;
		}
	}

	// Returns true if value is part of set.
	bool isElement(int value) const {
		for(int i = 0; i < _size; i++) {
			if(data[i] == value)
				return true;
		}
		return false;
	}

	// Returns an array containing the elements in the set.
	// This array is dynamically allocated an must be deleted by
	// the caller.
	int *getValues(int &sz) const {
		sz = _size;
		int *result = new int[_size];
		memcpy(result, data, _size * sizeof(int));
		return result;
	}
};

#endif
