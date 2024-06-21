def decode_message(triplets):
    shifts = []
    for i in range(26):
        shifted_moo = ''.join([chr((ord(letter) - ord('a') + i) % 26 + ord('a')) for letter in "moo"])
        if shifted_moo in triplets:
            shifts.append(i)
    return shifts

def generate_triplets(encoded):
    triplets = set()
    for i in range(len(encoded) - 2):
        triplets.add(encoded[i:i+3])
    
    return triplets

T = int(input())
for test_case in range(1, T + 1):
    encoded = input()
    triplets = generate_triplets(encoded)
    shifts=decode_message(triplets)
    print(test_case, *shifts)
