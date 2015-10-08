'''
Created on 8 Oct 2015

@author: PD
'''

submission = open('D:\\DataMining\\Hygiene\\hygiene_submission.txt','w')
 
submission.write("FTG\n")
 
with open("D:\\DataMining\\Hygiene\\hygiene_prediction.arff") as f:
    x = 1
    for line in f:
        values = line.split(",")
         
        if len(values) != 1:
            submission.write(values[2].split(":")[1] + "\n")
         
        x += 1
         
         
submission.close()