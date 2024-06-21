#include "testlib.h"
#include <iostream>
#include <vector>
#include <unordered_map>
#include <cmath>
#include <algorithm>

using namespace std;

vector<int> getRandomSubset(const std::vector<int>& block, int exclude,int allowed, unordered_map<int, bool>& includedInSubset) {
    vector<int> subset;
    if ((int)(block.size()) > 1) {
        allowed=min(allowed, (int)(block.size() - 1));
        while (allowed-->0) {
            int randomIndex = rnd.next(0, (int)(block.size()) - 1);
            while ((int)(block.size()) > 1 && block[randomIndex] == exclude) randomIndex = rnd.next(0, (int)(block.size()) - 1);
            includedInSubset[block[randomIndex]]=true;
            subset.push_back(block[randomIndex]);
        }    
    }
    
    return subset;
}

int main(int argc, char* argv[]) {
    registerGen(argc, argv, 1);

    int N = atoi(argv[1]);
    int P = atoi(argv[2]);

    vector<int> numbers(N);
    for (int i = 0; i < N; ++i) numbers[i] = i + 1;

    shuffle(numbers.begin(), numbers.end());

    vector<vector<int>> blocks;
    unordered_map<int, bool> whichBlock;
    unordered_map<int, int> blockGreatest;
    int remaining = N;
    while (remaining > 0) {
        int blockSize = rnd.next(1,remaining);
        vector<int> block;
        int greatest=0;
        int key=blocks.size();
        for (int i = 0; i < blockSize; ++i) {
            whichBlock[numbers[N - remaining]] = key;
            block.push_back(numbers[N - remaining]);
            remaining--;
            greatest=max(greatest,numbers[N - remaining]);
        }
        blocks.push_back(block);
        blockGreatest[key] = greatest;
    }

    unordered_map<int, bool> includedInSubset;
    
    int edgesLeft=1000000;
    cout << N << endl;
    for (int i = 1; i <= N; ++i) {
        cout << rnd.next(1, 1000000000) << " ";
        
        vector<int> block = blocks[whichBlock[i]];
        vector<int> subset = getRandomSubset(block, i, rnd.next(0, min(P, edgesLeft)), includedInSubset);
        if (i == blockGreatest[whichBlock[i]] && !includedInSubset[i] && block.size() > 1) subset.push_back(i);
        edgesLeft -= subset.size();
        edgesLeft=max(edgesLeft,0);
        
        cout << subset.size();
        for (int i : subset) cout << " " << i;
        cout << "\n";
    }
    
    return 0;
}