using System;

namespace Serializer {
	public class MySerializer {
		public static string Serialize(Object obj) {
			return "hello world";
		}
		public static T Deserialize<T>(string str) {
			return default(T);
		}
	}
	public class Point {
		public int x, y;
		public Point() {
			x = y = 0;
		}
		public Point(int X, int Y) {
			x = X;
			y = Y;
		}
	}
	public class Test {
		public static void Main(String [] args) {	
			Point p1 = new Point(2, 3);
			String str1 = MySerializer.Serialize(p1);
			Console.WriteLine(str1);
			Point newPt = MySerializer.Deserialize<Point>(str1);
			Console.WriteLine(newPt);
		}
	}
}

