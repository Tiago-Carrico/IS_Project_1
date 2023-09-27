#NOTE: do not forget to run the script with a element number as the only argument (don't just click the run button on vscode)
#If need be, just click run on vscode, up arrow on the terminal and add the number of element at the end

import random
import os
import sys

here = os.path.dirname(os.path.abspath(__file__))

firstNameFile = os.path.join(here, 'firstNames.txt')
lastNameFile = os.path.join(here, 'lastNames.txt')
emailsFile = os.path.join(here, 'emails.txt')

#Works no problem
def readData():
    f = open(firstNameFile, "r")
    firstNames = []
    for line in f:
        line = line.strip("\n")
        line = line.strip(";")
        firstNames.append(line)

    f = open(lastNameFile, "r")
    lastNames = []
    for line in f:
        line = line.strip("\n")
        line = line.strip(";")
        lastNames.append(line)

    f = open(emailsFile, "r")
    emails = []
    for line in f:
        line = line.strip("\n")
        line = line.strip(";")
        emails.append(line)

    return firstNames, lastNames, emails

#Works no problem
def createEmail(list):
    emailStr = ""
    for i in range(10):
        emailStr = emailStr + random.choice(list)

    return emailStr

def concEmail(body, sufix):
    return body + sufix

if __name__ == "__main__":
    elementNumber = int(sys.argv[1])

    firstNames, lastNames, emails = readData()
    abc = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']

    #Starting to write on file
    f = open(here + "\\testDataFolder\\testData" + str(elementNumber) + ".txt", "w")
    f.write(str(elementNumber) + "\n")
    for j in range(elementNumber):
        currInfo = ""
        currInfo = currInfo + str(j + 1)
        currInfo = currInfo + ";"
        currInfo = currInfo + random.choice(firstNames)
        currInfo = currInfo + " "
        currInfo = currInfo + random.choice(lastNames)
        currInfo = currInfo + ";"
        currInfo = currInfo + concEmail(createEmail(abc), random.choice(emails))
        currInfo = currInfo + ";\n"
        f.write(str(currInfo))