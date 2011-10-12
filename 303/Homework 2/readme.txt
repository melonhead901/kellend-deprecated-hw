# Kellen Donohue
# CSE 303 HW 2
# Readme
# 1-25-09

To solve the problem of extraneous input I ran my parse.sh output data through a few more sed "filters". First, I removed any lines with 100bestwebsites.html in them. Then, I removed the first line, as it had some garbage data. Finally, I removed any blank lines using another sed filter.
