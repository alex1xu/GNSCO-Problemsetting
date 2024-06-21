import os
import random
import string

def generate_test_case(folder_path, test_case_number,message_length=-1):
    file_name = os.path.join(folder_path, f"test_case_{test_case_number}.txt")
    with open(file_name, 'w') as f:
      T=random.randint(1, 10**3)
      f.write(str(T)+'\n')
      for __ in range(T):
        if message_length==-1:
          message_length = random.randint(3, 10**5)

        encoded_message = ''
        for _ in range(message_length):
            encoded_message += random.choice(string.ascii_lowercase + ' ')
        
        f.write(encoded_message+'\n')

def main():
    folder_path = "tests"
    os.makedirs(folder_path, exist_ok=True)
    # for i in range(1, 51):
    #     generate_test_case(folder_path, i)
    generate_test_case(folder_path, 100,10**5)
    generate_test_case(folder_path, 101,10**5)
    # generate_test_case(folder_path, 102,10**5)
    # generate_test_case(folder_path, 103,10**5)

if __name__ == "__main__":
    main()
