package com.LearnToCrypt.DAO;

import java.util.ArrayList;
import java.util.List;

import com.LearnToCrypt.BusinessModels.Algorithm;

public class AlgorithmDAOFactoryMock implements IAlgorithmDAO {

	ArrayList<Algorithm> algorithms;

	public AlgorithmDAOFactoryMock() {
		this.algorithms = new ArrayList<>();
		addAlgorithm();
	}

	private void addAlgorithm() {
		Algorithm algorithm1 = new Algorithm();
		algorithm1.setName("Caesar Cipher");
		algorithm1.setDescription("The action of a Caesar cipher is to replace each plaintext letter with a different one a fixed number of places down the alphabet. The cipher illustrated here uses a left shift of three, so that (for example) each occurrence of E in the plaintext becomes B in the ciphertext.");
		algorithm1.setType("Basic substitution cipher");
		algorithm1.setDescriptionShort("The action of a Caesar cipher is to replace each plaintext letter with a different one a fixed number of places down the alphabet.");
		algorithm1.setLevel(1);
		algorithm1.setImage("Caesar_cipher.png");

		Algorithm algorithm2 = new Algorithm();
		algorithm2.setName("Matrix Transposition Cipher");
		algorithm2.setDescription("The Matrix Transposition cipher is a transposition cipher as the name suggests. \n" +
				" That means all of the characters in the plaintext are also present in the encrypted ciphertext. This cipher only changes \n" +
				" the arrangement of the words according to the key specified. The matrix transposition cipher first converts all the \n" +
				" space characters to '%' characters. It then converts the input text into a two dimensional matrix with the number \n" +
				" of columns determined by the key supplied to the cipher. Any vacancies left in the matrix after the plaintext is \n" +
				" added are filled by the character '%'. The key is a permutation of these columns. The ciphertext is constructed \n" +
				" by reading the the matrix column-wise in the order specified by the key.");
		algorithm2.setType("Transposition Cipher");
		algorithm2.setDescriptionShort("The Matrix Transposition cipher converts the input text into a matrix and then reads it column-wise based on the key specified.");
		algorithm2.setLevel(2);
		algorithm2.setImage("Matrix_Cipher.png");

		algorithms.add(algorithm1);
		algorithms.add(algorithm2);
	}

	@Override
	public Algorithm getAlgorithm(String algorithmName) {
		for (Algorithm a:algorithms) {
			if (a.getName().equals(algorithmName)){
				return a;
			}
		}
		return null;
	}

	@Override
	public ArrayList<String> getAllAvailableAlgorithm() {
		ArrayList<String> toReturn = new ArrayList<>();
		for (Algorithm a:algorithms) {
			toReturn.add(a.getName());
		}
		return toReturn;
	}

	@Override
	public ArrayList<Algorithm> getAlgorithmByLevelAndClass(int lv, String className) {
		return algorithms;
	}

	@Override
	public String[] getAlgList(String className) {
		// TODO Auto-generated method stub
		String[] toRetuen = {"Caesar Cipher","Matrix Transposition Cipher"};
		return toRetuen;
	}
}
