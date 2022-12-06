import re

contents = ""
with open('input.txt', 'r') as data:
    for row in data:
        contents += row
nums = re.split("\D", contents)
count = 0
for i in range(0, len(nums)-1, 4):
    first_pair_first = int(nums[i])
    first_pair_second = int(nums[i + 1])
    secod_pair_first = int(nums[i + 2])
    second_pair_second = int(nums[i + 3])
    if (first_pair_first <= secod_pair_first and first_pair_second >= second_pair_second) or (first_pair_first >= secod_pair_first and first_pair_second <= second_pair_second) :
        count+=1
print(count)