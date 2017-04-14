using System;
using System.Reflection;
using System.Collections.Generic;

namespace Serializer {
	public class MySerializer {

		private static Char[] delims = {'\t', '\n'};

		public static string Serialize(Object obj) {
			String str = "";
			Type type = obj.GetType();
			str += String.Format(type.ToString() + "\n");
			foreach(FieldInfo info in type.GetFields()) {
				if(info.MemberType == MemberTypes.Field) {
					str += String.Format("{0}\t{1}\t{2}\n", info.Name, info.GetValue(obj), info.FieldType);
				}
			}
			return str.Trim();
		}

		public static T Deserialize<T>(string str) {
			Type type = typeof(T);
			ConstructorInfo ctor = type.GetConstructor(new Type[] {});
			T obj = (T)ctor.Invoke(new Object[] {});
			List<string> members = new List<string>(str.Split(delims));

			int i = 1;
			foreach(FieldInfo info in type.GetFields()) {
				String fieldName = members[i];
				String fieldValue = members[i+1];
				String fieldType = members[i+2];

				if(fieldType == "System.Int32") {
					Set(obj, fieldName, Int32.Parse(fieldValue));
				}
				if(fieldType == "System.Double") {
					Set(obj, fieldName, Double.Parse(fieldValue));
				}
				if(fieldType == "System.Boolean") {
					Set(obj, fieldName, Boolean.Parse(fieldValue));
				}
				i+=3;
			}
			return obj;
		}

		public static void Set<T>(T o, String fieldName, Object v) {
			Type t = o.GetType();
			FieldInfo info = t.GetField(fieldName);
			info.SetValue(o, v);
		}
	}

	public class Point {
		public int x, y;
		public double z;
		public bool boolean;
		public Point() {
			x = y = 0;
			z = 0.0;
			boolean = false;
		}
		public Point(int X, int Y, double Z, bool Bool) {
			x = X;
			y = Y;
			z = Z;
			boolean = Bool;
		}

		public override string ToString() {
			return String.Format("x = ({0}) y = ({1}) z = ({2}) boolean = ({3})\n", x, y, z, boolean);
		}
	}
	public class Test {
		public static void Main(String [] args) {
			Point p1 = new Point(2, 3, 1.4, true);
			String str1 = MySerializer.Serialize(p1);
			Console.WriteLine(str1);
			Point newPt = MySerializer.Deserialize<Point>(str1);
			Console.WriteLine(newPt);
		}
	}
}

