using System;
using System.IO;
using System.Collections.Generic;

public class Record {

	public Dictionary<string, string> info;

	public Record() {
		info = new Dictionary<string, string>();
	}

	public string GetValuesAsString() {
		string str = "";
		foreach(string key in info.Keys) {
			str += info[key] + "\t";
		}
		return str;

	}

	public void CreateOutputFile(string template) {
		string filename = info["ID"] + ".txt";
		string output = template;

		foreach(string key in info.Keys) {
			output = output.Replace("<<" + key + ">>", info[key]);
		}

		if(File.Exists(filename)) {
			File.Delete(filename);
		}

		using(StreamWriter sw = File.CreateText(filename)) {
			sw.WriteLine(output);
		}

	}

	public override string ToString() {
		string str = "";
		string str2 = "";
		foreach(string key in info.Keys) {
			str += key + "\t";
			str2 += info[key] + "\t";
		}
		return str + "\n" + str2;
	}
}

public class Parser {

	private List<string> columnIDs;
	private static Char[] delims = {'\t'};
	private List<Record> records = new List<Record>();
	private string template;

	public string GetEntriesAsString() {
		string str = "";
		foreach(Record r in records) {
			str += r.GetValuesAsString() + "\n";
		}
		return str;
	}

	public void ParseFile(string fileName, string templateFileName) {
		using(StreamReader sr = new StreamReader(templateFileName)) {
			string line;
			while((line = sr.ReadLine()) != null) {
				template += line + "\n";
			}
			template = template.Trim();
		}

		using(StreamReader sr = new StreamReader(fileName)) {

			string line;
			// Read IDs
			line = sr.ReadLine();
			columnIDs = new List<string>(line.Split(delims));

			while((line = sr.ReadLine()) != null) {
				List<string> column_info = new List<string>(line.Split(delims));
				Record r = new Record();
				for(int i = 0; i < columnIDs.Count; i++) {
					r.info.Add(columnIDs[i], column_info[i]);
				}
				records.Add(r);
			}
		}

		foreach(Record r in records) {
			r.CreateOutputFile(template);
		}
	}
}

public class MM {

	public static void Main(string[] args) {

		Parser p = new Parser();

		if(args.Length > 1) {
			p.ParseFile(args[0], args[1]);
		}
		else {
			Console.WriteLine("Not enough input files given...");
		}
	}
}
