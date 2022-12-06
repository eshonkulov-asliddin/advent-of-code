import re

contents = ""
with open('input.txt', 'r') as data:
    for row in data:
        contents += row
nums = re.split("\D", contents)
overlaps = 0
print(len(nums))
for i in range(0, len(nums), 4):
    first_pair_first = int(nums[i])
    first_pair_second = int(nums[i + 1])
    second_pair_first = int(nums[i + 2])
    second_pair_second = int(nums[i + 3])
    if (second_pair_first >= first_pair_first and second_pair_first <= first_pair_second)   or \
       (second_pair_second >= first_pair_first and second_pair_second <= first_pair_second) or \
       (first_pair_first >= second_pair_first and first_pair_first <= second_pair_second)   or \
       (first_pair_second >= second_pair_first and first_pair_second <= second_pair_second):

        overlaps+=1
print(overlaps)
