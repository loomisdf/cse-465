using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UTMatrix {

	// This iterator iterates over the upper triangular matrix.
	// This is done in a row major fashion, starting with [0][0],
	// and includes all N*N elements of the matrix.
	public class UTMatrixEnumerator : IEnumerator {
		public UTMatrix matrix;
		public int currRow;
		public int currCol;

		public UTMatrixEnumerator(UTMatrix matrix) {
			this.matrix = matrix;
			Reset();
		}

		public void Reset() {
			currRow = -1;
			currCol = -1;
		}

		public bool MoveNext() {
			if(currRow == -1) {
				currRow++;
			}
			if(currCol == matrix.getSize() - 1) {
				currCol = 0;
				currRow++;
			}
			else {
				currCol++;
			}

			return currRow < matrix.getSize();
		}

		object IEnumerator.Current {
			get {
				return Current;
			}
		}

		public int Current {
			get {
				try {
					return matrix.get(currRow, currCol);
				}
				catch (IndexOutOfRangeException) {
					throw new InvalidOperationException();
				}
			}
		}
	}
	public class UTMatrix : IEnumerable {
		// Must use a one dimensional array, having minumum size.
		public int [] data;
		public int _num_elements;
		public int _n;

		// Construct an NxN Upper Triangular Matrix, initialized to 0
		// Throws an error if N is non-sensical.
		public UTMatrix(int N) {
			if(N == 1) {
				// Throw error
			}
			_n = N;
			_num_elements = (int)(Math.Pow(N, 2) + N) / 2;
			data = new int[_num_elements];
		}

		// Returns the size of the matrix
		public int getSize() {
			return _n;
		}

		// Returns an upper triangular matrix that is the summation of a & b.
		// Throws an error if a and b are incompatible.
		public static UTMatrix operator + (UTMatrix a, UTMatrix b) {
			if(a._n != b._n) {
				Console.WriteLine("Summation Error: Matrices are incompatible");
				return null;
			}
			UTMatrix ret = new UTMatrix(a._n);
			for(int i = 0; i < ret._num_elements; i++) {
				ret.data[i] = a.data[i] + b.data[i];
			}
			return ret;
		}

		// Set the value at index [r][c] to val.
		// Throws an error if [r][c] is an invalid index to alter.
		public void set(int r, int c, int val) {
			int i = accessFunc(r, c);
			if(i == -1) {
				return;
			}
			data[i] = val;
		}

		// Returns the value at index [r][c]
		// Throws an error if [r][c] is an invalid index
		public int get(int r, int c) {
			int i = accessFunc(r, c);
			if(i == -1) {
				return 0;
			}
			return data[i];
		}

		// Returns the position in the 1D array for [r][c].
		// Throws an error if [r][c] is an invalid index
		public int accessFunc(int r, int c) {
			int i = (_n * r) + c - ((r * (r + 1)) / 2);
			if (r > c) {
				i = -1;
			}
			return i;
		}

		// Returns an enumerator for an upper triangular matrix
		IEnumerator IEnumerable.GetEnumerator() {
			return GetEnumerator();
		}

		public UTMatrixEnumerator GetEnumerator() {
			return new UTMatrixEnumerator(this);
		}

		// For debug purposes
		public void PrintMatrix() {
			for(int r = 0; r < this.getSize(); r++) {
				for(int c = 0; c < this.getSize(); c++) {
					Console.Write(this.get(r, c) + " ");
				}
				Console.Write("\n");
			}
		}

		public static void Main(String [] args) {
			const int N = 5;
			UTMatrix ut1 = new UTMatrix(N);
			UTMatrix ut2 = new UTMatrix(N);
			for (int r=0; r<N; r++) {
				ut1.set(r, r, 1);
				for (int c=r; c<N; c++) {
					ut2.set(r, c, 1);
				}
			}
			UTMatrix ut3 = ut1 + ut2;
			UTMatrixEnumerator ie = ut3.GetEnumerator();
			while (ie.MoveNext()) {
				Console.Write(ie.Current + " ");
			}
			Console.WriteLine();
			foreach (int v in ut3) {
				Console.Write(v + " ");
			}
			Console.WriteLine();
		}
	}
}
