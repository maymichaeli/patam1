package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class BloomFilter {
    private final BitSet bitSet;
    private final int size;
    private final List<MessageDigest> hashFunctions;

    public BloomFilter(int size, String... algorithms) {
        this.size = size;
        this.bitSet = new BitSet(size);
        this.hashFunctions = new ArrayList<>();

        for (String algorithm : algorithms) {
            try {
                MessageDigest md = MessageDigest.getInstance(algorithm);
                hashFunctions.add(md);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Algorithm not found: " + algorithm, e);
            }
        }
    }

    public void add(String item) {
        byte[] bytes = item.getBytes();
        for (MessageDigest md : hashFunctions) {
            md.reset();
            byte[] hashBytes = md.digest(bytes);
            BigInteger hashValue = new BigInteger(hashBytes);
            int num = hashValue.intValue();
            num = Math.abs(num); // Ensure the index is positive
            int index = num % size; // Ensure the index fits within the size of the BitSet
            bitSet.set(index);
        }
    }

    public boolean contains(String item) {
        byte[] bytes = item.getBytes();
        for (MessageDigest md : hashFunctions) {
            md.reset();
            byte[] hashBytes = md.digest(bytes);
            BigInteger hashValue = new BigInteger(hashBytes);
            int num = hashValue.intValue();
            num = Math.abs(num); // Ensure the index is positive
            int index = num % size; // Ensure the index fits within the size of the BitSet
            if (!bitSet.get(index)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(bitSet.length());
        for (int i = 0; i < bitSet.length(); i++) {
            sb.append(bitSet.get(i) ? '1' : '0');
        }
        return sb.toString();
    }
}

// import java.math.BigInteger;
// import java.security.MessageDigest;
// import java.security.NoSuchAlgorithmException;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.BitSet;
// import java.util.List;

// public class BloomFilter {
//     private final BitSet bitSets;
//     private int size;
//     private List<MessageDigest> hashFunctions;

//     public BloomFilter(int size, String... algs) {
//         this.size = size;
//         this.bitSets = new BitSet(size);
//         this.hashFunctions = new ArrayList<>();

//         for (String alg : algs) {
//             try {
//                 this.hashFunctions.add(MessageDigest.getInstance(alg));
//             } catch (NoSuchAlgorithmException e) {
//                 e.printStackTrace();
//             }
//         }
//     }

//     // public void add(String s) {
//     //     System.out.println("Adding: " + s);
//     //     for (MessageDigest md : hashFunctions) {
//     //         byte[] hashBytes = md.digest(s.getBytes());
//     //         BigInteger hashValue = new BigInteger(1, hashBytes);
//     //         int index = hashValue.mod(BigInteger.valueOf(size)).intValue();
//     //         System.out.println("HashBytes: " + new BigInteger(1, hashBytes).toString(16));
//     //         System.out.println("HashValue: " + hashValue.toString(16));
//     //         System.out.println("Index: " + index);
//     //         bitSets.set(index);
//     //     }
//     // }
    
//     // public boolean contains(String s) {
//     //     for (MessageDigest md : hashFunctions) {
//     //         byte[] hashBytes = md.digest(s.getBytes());
//     //         BigInteger hashValue = new BigInteger(1, hashBytes);
//     //         int index = hashValue.mod(BigInteger.valueOf(size)).intValue();
//     //         //System.out.println("Checking: " + s + " Hash: " + hashValue.toString(16) + " Index: " + index);
//     //         if (!bitSets.get(index)) {
//     //             return false;
//     //         }
//     //     }
//     //     return true;
//     // }
   
//     @Override
//     public String toString() {
//         StringBuilder sb = new StringBuilder();
//         for (int i = 0; i < size; i++) {
//             sb.append(bitSets.get(i) ? '1' : '0');
//         }
//         System.out.println(sb.toString());
//         return sb.toString();
//     }

// }
