# Programming Assignment 1: Visualize Data Using a Chart

## Overview
This assignment will give you a chance to explore the topics covered in week 2 of the course by visualizing some data as a chart. The data set we provided deals with world temperatures and comes from NASA: http://data.giss.nasa.gov/gistemp/. Alternatively you can use any data that you would like to explore. You are not required to use D3.js, but if you would like to, we have provided some helpful resources that will make it easier for you to create a visualization. You are welcome to use the additional resources, especially if you do not want to program to complete this project.

## Goals
The goal of this assignment is to give you some experience with handling and deciding how to visualize some data and for you to get a feel for the various aspects of the visualization.

This assignment will also help you to analyze other visualizations and assess their effectiveness.

## Time Estimation
This is not a tricky assignment, but the amount of time that it takes will vary based on the tools you use and the amount of customization you would like to do in your chart.

## Instructions
1. Take the data from the GISTEMP site, specifically the data from “Table Data: Global and Hemispheric Monthly Means and Zonal Annual Means.” Alternatively you can use any data that you would like to explore instead.
2. Parse the data to a suitable format for the tools that you are using – we have provided two files (in JS, TXT, and CSV formats) that contain the data used to make the visualizations here, which is a subset of the data on the GISTEMP site.
3. Visualize the data in any meaningful way you wish, keeping in mind the requirements of the Programming Assignment 1 Rubric.  
4. Click to download the .zip file for this programming assignment. Here is the original data from the GISTEMP site.

If you need them, there are free-to-use websites that you can use to write and execute D3 code:
* http://code.runnable.com (see also http://code.runnable.com/d3.js for examples)
* https://vida.io

### Log of Changes to the Data
We realize that we have updated the data provided from our side multiple times, so this a quick log of the changes:

1. We updated the .zip by adding the CSV and TXT files, in response to a request.

2. There was a formatting issue (there were commas in incorrect places) in the files which caused confusion. The formatting was changed to become clearer.

3. It was pointed out that the data from the GISTEMP site did not match the data we had provided on our side. This was because there had been an update to the GISTEMP data, due to a bug which they had found, since the time we created the .zip on our side. We overlooked this update, which is why the data had differed. A small formatting change was also made for clarity. In the second DATA2 files, there are no longer spaces in the longitudinal demarcations. From "24N -90N", it is now "24N-90N", and likewise.

## Submission
You must upload an image (or a URL to a website displaying an image) of your visualization for peer evaluation.

Alongside your visualizations, feel free to include a paragraph that helps explain your submission.

A few questions that your paragraph could answer:

1. What are your X and Y axes?
2. Did you use a subset of the data? If so, what was it?
3. Are there any particular aspects of your visualization to which you would like to bring attention?
4. What do you think the data, and your visualization, shows?