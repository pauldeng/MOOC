'''
Created on 5 Oct 2015

@author: PD
'''

with open("D:\\DataMining\\Hygiene\\hygiene.dat.labels") as f:
    content = f.readlines()
     
with open("D:\\DataMining\\Hygiene\\hygiene.dat.additional") as f:
    additional = f.readlines()
  
arffTrainFile = open('D:\\DataMining\\Hygiene\\hygiene_2_train.arff','w')
arffTestFile = open('D:\\DataMining\\Hygiene\\hygiene_2_test.arff','w')
  
arffTrainFileHeader = "@relation hygiene_train\n\n"
arffTestFileHeader = "@relation hygiene_test\n\n"
 
arffFileHeader = "@attribute Text string\n@attribute class-att {0,1}\n\n@data\n\n" 
# arffFileHeader = "@attribute Text string\n@attribute 'rating' numeric\n@attribute class-att {0,1}\n\n@data\n\n"
  
   
with open("D:\\DataMining\\Hygiene\\hygiene.dat") as f:
    x = 0
      
    arffTestFile.write(arffTestFileHeader + arffFileHeader)
    arffTrainFile.write(arffTestFileHeader + arffFileHeader)
      
    for line in f:
        line = line[:-1].replace("'", "\\'")
        rating = additional[x].split("]\",")[1].split(",")[2][:-1]
         
        if "None" in content[x]:
            arffTestFile.write("'" + line + "',?\n")
#             arffTestFile.write("'" + line + "'," + rating + ",?\n")
        else:
            arffTrainFile.write("'" + line + "'," + content[x][:-1] + "\n")
#             arffTrainFile.write("'" + line + "'," + rating + "," + content[x][:-1] + "\n")
        x += 1
  
arffTrainFile.close()
arffTestFile.close()
