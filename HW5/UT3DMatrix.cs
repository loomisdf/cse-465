using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UpperTriangularMatrix {
	// This iterator iterates over the upper triangular matrix.
	// This is done in a row major fashion, starting with [0][0],
	// and includes all N*N elements of the matrix.
	public class UT3DMatrixEnumerator : IEnumerator {
		public UT3DMatrixEnumerator(UT3DMatrix matrix) {
		}
		public void Reset() {
		}
		public bool MoveNext() {
			return false;
		}
		object IEnumerator.Current {
			get {
				return Current;
			}
		}
		public int Current {
			get {
				try {
					return 0;
				}
				catch (IndexOutOfRangeException) {
					throw new InvalidOperationException();
				}
			}
		}
	}
	public class UT3DMatrix : IEnumerable {
		// Must use a one dimensional array, having minumum size.
		public int [] data;

		// Construct an NxN Upper Triangular Matrix, initialized to 0
		// Throws an error if N is non-sensical.
		public UT3DMatrix(int N) {
		}
		// Returns the size of the matrix
		public int getSize() {
			return -1;
		}
		// Returns an upper triangular matrix that is the summation of a & b.
		// Throws an error if a and b are incompatible.
		public static UT3DMatrix operator + (UT3DMatrix a, UT3DMatrix b) {
			return null;
		}
		// Set the value at index [r][c][d] to val.
		// Throws an error if [r][c][d] is an invalid index to alter.
		public void set(int r, int c, int d, int val) {
		}
		// Returns the value at index [r][c][d]
		// Throws an error if [r][c][d] is an invalid index
		public int get(int r, int c, int d) {
			return 0;
		}
		// Returns the position in the 1D array for [r][c].
		// Throws an error if [r][c] is an invalid index
		public int accessFunc(int r, int c) {
			return 0;
		}
		// Returns an enumerator for an upper triangular matrix
		IEnumerator IEnumerable.GetEnumerator() {
			return GetEnumerator();
		}
		public UT3DMatrixEnumerator GetEnumerator() {
			return new UT3DMatrixEnumerator(this);
		}

		public static void Main(String [] args) {
			const int N = 5;
			UT3DMatrix ut1 = new UT3DMatrix(N);
			UT3DMatrix ut2 = new UT3DMatrix(N);
			for (int r=0; r<N; r++) {
				ut1.set(r, r, r, 1);
				for (int c=r; c<N; c++) {
					for (int d=c; d<N; d++) {
						ut2.set(r, c, d, 1);
					}
				}
			}
			UT3DMatrix ut3 = ut1 + ut2;
			UT3DMatrixEnumerator ie = ut3.GetEnumerator();
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
