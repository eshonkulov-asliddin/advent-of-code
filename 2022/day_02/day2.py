contents = []

score_for_each = {
    "Rock":1,
    "Paper": 2,
    "Scissors":3
}

my_answers = {
    "X": "Rock",
    "Y": "Paper",
    "Z": "Scissors"
}

opponents_answers = {
    "A": "Rock",
    "B": "Paper",
    "C": "Scissors"
}



while True:
    try:
        line = input()
    except:
        break
    contents.append(line)
print(contents)

count_my_score = 0
length = len(contents)
for turns in contents:
    opponents_choice = turns[0]
    my_choice = turns[2]

    #second star solution
    if my_choice == "X":
        if opponents_answers[opponents_choice] == "Rock":
            count_my_score += score_for_each["Scissors"]
        elif opponents_answers[opponents_choice] == "Scissors":
            count_my_score += score_for_each["Paper"]
        else:
            count_my_score += score_for_each["Rock"]
    elif my_choice == "Y":
        if opponents_answers[opponents_choice] == "Rock":
            count_my_score += score_for_each["Rock"]
        elif opponents_answers[opponents_choice] == "Scissors":
            count_my_score += score_for_each["Scissors"]
        else:
            count_my_score += score_for_each["Paper"]
        count_my_score += 3
    elif my_choice == "Z":
        if opponents_answers[opponents_choice] == "Rock":
            count_my_score += score_for_each["Paper"]
        elif opponents_answers[opponents_choice] == "Scissors":
            count_my_score += score_for_each["Rock"]
        else:
            count_my_score += score_for_each["Scissors"]
        count_my_score += 6

    ## first star solution
    # if my_answers[my_choice] == "Rock" and opponents_answers[opponents_choice] == "Scissors":
    #     count_my_score += score_for_each[my_answers[my_choice]]
    #     count_my_score += 6
    # elif my_answers[my_choice] == "Scissors" and opponents_answers[opponents_choice] == 'Paper':
    #     count_my_score += score_for_each[my_answers[my_choice]]
    #     count_my_score += 6
    # elif my_answers[my_choice] == "Paper" and opponents_answers[opponents_choice] == "Rock":
    #     count_my_score += score_for_each[my_answers[my_choice]]
    #     count_my_score += 6
    # elif (my_answers[my_choice] == opponents_answers[opponents_choice]):
    #     count_my_score += score_for_each[my_answers[my_choice]]
    #     count_my_score += 3
    #
    # else:
    #     count_my_score += score_for_each[my_answers[my_choice]]


print(count_my_score)

##################### different approach ############################
# d = {
#         "A X": 3,
#         "A Y": 4,
#         "A Z": 8,
#         "B X": 1,
#         "B Y": 5,
#         "B Z": 9,
#         "C X": 2,
#         "C Y": 6,
#         "C Z": 7,
# }
#
# contents = []
# while True:
#     try:
#         line = input()
#     except:
#         break
#     contents.append(line)
#
# count_score = 0
# for content in contents:
#         count_score += d[content]
# print(count_score)