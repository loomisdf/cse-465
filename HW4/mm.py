import sys

class Record:
    def __init__(self):
        self.info = {}

    def addRecord(self, col_id, col_data):
        self.info[col_id] = col_data

    def createOutputFile(self, template):
        filename = self.info['ID'] + '.txt'
        output = template
        for key in self.info.keys():
            output = output.replace('<<' + key + '>>', self.info[key])

        with open(filename, 'w') as outputFile:
            outputFile.write(output)

class Parser:
    def __init__(self):
        self.columnIDs = []
        self.template = ""
        self.records = []

    def parseFile(self, fileName, templateFileName):
        with open(templateFileName, 'r') as templateFile:
            for line in templateFile:
                self.template += line

        self.template = self.template.strip()

        with open(fileName, 'r') as dataFile:
            columnIDs = [word.strip() for word in dataFile.readline().split('\t')]

            for line in dataFile:
                column_data = [word.strip() for word in line.split('\t')]
                r = Record()
                for i in range(len(columnIDs)):
                    r.addRecord(columnIDs[i], column_data[i])
                self.records.append(r)

        for record in self.records:
            record.createOutputFile(self.template)


if __name__ == "__main__":

    parser = Parser()

    if(len(sys.argv) <= 4 and len(sys.argv) > 1):
        parser.parseFile(sys.argv[1], sys.argv[2])
    else:
        print("Wrong number of program arguments")
