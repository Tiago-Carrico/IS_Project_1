import os
import sys

here = os.path.dirname(os.path.abspath(__file__))
 
# directory name from which
# we are going to extract our files with its size
path_XML = "is1\\src\\outputXML"
path_Proto = "is1\\src\\outputProto"
 
# Get list of all files only in the given directory
fun_XML = lambda x : os.path.isfile(os.path.join(path_XML,x))
files_list_XML = filter(fun_XML, os.listdir(path_XML))

fun_Proto = lambda x : os.path.isfile(os.path.join(path_Proto,x))
files_list_Proto = filter(fun_Proto, os.listdir(path_Proto))
 
# Create a list of files in directory along with the size
size_of_file_XML = [
    (f,os.stat(os.path.join(path_XML, f)).st_size)
    for f in files_list_XML
]

size_of_file_Proto = [
    (f,os.stat(os.path.join(path_Proto, f)).st_size)
    for f in files_list_Proto
]

# Opening file writers
f_XML = open(here + "\\FileSizeResults\\XML_sizes.txt", "w")
f_Proto = open(here + "\\FileSizeResults\\Proto_sizes.txt", "w")

# Iterate over list of files along with size
# and print them one by one.
for f,s in size_of_file_XML:
    #print("{} : {}KB".format(f, round(s/(1024),3)))
    f_XML.write("{} : {} KB\n".format(f, round(s/(1024),3)))




for f,s in size_of_file_Proto:
    #print("{} : {}KB".format(f, round(s/(1024),3)))
    f_Proto.write("{} : {} KB\n".format(f, round(s/(1024),3)))