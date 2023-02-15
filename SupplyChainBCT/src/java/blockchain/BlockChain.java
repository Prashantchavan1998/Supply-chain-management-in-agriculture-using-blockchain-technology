/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain;

import java.security.Security;
import java.util.ArrayList;
import java.util.Base64;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import pack.DBConnection;
import pack.DBConnection1;
//import pack.DbConnection;
//import pack.DbConnection1;

/**
 *
 * @author Dinesh
 */
public class BlockChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();

    public static int difficulty = 3;
    public static float minimumTransaction = 0.1f;
    public static Wallet walletA;
    public static Wallet walletB;
    public static Transaction genesisTransaction;

    public static boolean insertBlock(String[] args) {
        try {

            //add our blocks to the blockchain ArrayList:
            String sender_id = args[0];
            String receiver_id = args[1];
            String crop_name = args[2];
            String quantity = args[3];
            String rate = args[4];
            double amount = Double.parseDouble(args[5]);

            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); //Setup Bouncey castle as a Security Provider

            //Create wallets:
            walletA = new Wallet();
            walletB = new Wallet();
            Wallet coinbase = new Wallet();

            //create genesis transaction, which sends 100 NoobCoin to walletA: 
            genesisTransaction = new Transaction(coinbase.publicKey, walletA.publicKey, 100f, null);
            genesisTransaction.generateSignature(coinbase.privateKey);	 //manually sign the genesis transaction	
            genesisTransaction.transactionId = "0"; //manually set the transaction id
            genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient, genesisTransaction.value, genesisTransaction.transactionId)); //manually add the Transactions Output
            UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0)); //its important to store our first transaction in the UTXOs list.

            String prevHash = "0";
            DBConnection db = new DBConnection();
            DBConnection1 db1 = new DBConnection1();
            ResultSet rs = db.select("SELECT * FROM tbl_transactions");
            while (rs.next()) {
                prevHash = rs.getString("current_hash");
            }
            System.out.println("Creating and Mining Genesis block... ");

            Block genesis = new Block(prevHash);

            boolean flag = isChainValid();
            if (flag) {
                String sql = "INSERT INTO tbl_transactions(sender_id,receiver_id,crop_name,quantity,rate,amount,timestamp,prev_hash,current_hash) VALUES("
                        + "'" + sender_id + "','" + receiver_id + "','" + crop_name + "','" + quantity + "','" + rate + "','" + amount + "','" + genesis.timeStamp + "','" + prevHash + "','" + genesis.hash + "')";
                int row_affected = db.update(sql);
                row_affected = db1.update(sql);
                if (row_affected > 0) {
                    sql = "UPDATE tbl_bank_details SET balance=balance-" + amount + " WHERE user_id='" + receiver_id + "'";
                    row_affected = db.update(sql);
                    row_affected = db1.update(sql);
                    sql = "UPDATE tbl_bank_details SET balance=balance+" + amount + " WHERE user_id='" + sender_id + "'";
                    row_affected = db.update(sql);
                    row_affected = db1.update(sql);
                }
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            //add our blocks to the blockchain ArrayList:
            String sender_id = args[0];
            String receiver_id = args[1];
            String crop_name = args[2];
            String quantity = args[3];
            String rate = args[4];
            String amount = args[5];

            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); //Setup Bouncey castle as a Security Provider

            //Create wallets:
            walletA = new Wallet();
            walletB = new Wallet();
            Wallet coinbase = new Wallet();
           
            //create genesis transaction, which sends 100 NoobCoin to walletA: 
            genesisTransaction = new Transaction(coinbase.publicKey, walletA.publicKey, 100f, null);
            genesisTransaction.generateSignature(coinbase.privateKey);	 //manually sign the genesis transaction	
            genesisTransaction.transactionId = "0"; //manually set the transaction id
            genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient, genesisTransaction.value, genesisTransaction.transactionId)); //manually add the Transactions Output
            UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0)); //its important to store our first transaction in the UTXOs list.

            String prevHash = "0";
            DBConnection db = new DBConnection();
            DBConnection1 db1 = new DBConnection1();
            ResultSet rs = db.select("SELECT * FROM tbl_transactions");
            while (rs.next()) {
                prevHash = rs.getString("current_hash");
            }
            System.out.println("Creating and Mining Genesis block... ");

            Block genesis = new Block(prevHash);

            boolean flag = isChainValid();
            if (flag) {
                String sql = "INSERT INTO tbl_transactions(sender_id,receiver_id,crop_name,quantity,rate,amount,timestamp,prev_hash,current_hash) VALUES("
                        + "'" + sender_id + "','" + receiver_id + "','" + crop_name + "','" + quantity + "','" + rate + "','" + amount + "','" + genesis.timeStamp + "','" + prevHash + "','" + genesis.hash + "')";
                int row_affected = db.update(sql);
                row_affected = db1.update(sql);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        HashMap<String, TransactionOutput> tempUTXOs = new HashMap<String, TransactionOutput>(); //a temporary working list of unspent transactions at a given block state.
        tempUTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));

        //loop through blockchain to check hashes:
        for (int i = 1; i < blockchain.size(); i++) {

            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("#Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("#Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("#This block hasn't been mined");
                return false;
            }

            //loop thru blockchains transactions:
            TransactionOutput tempOutput;
            for (int t = 0; t < currentBlock.transactions.size(); t++) {
                Transaction currentTransaction = currentBlock.transactions.get(t);

                if (!currentTransaction.verifiySignature()) {
                    System.out.println("#Signature on Transaction(" + t + ") is Invalid");
                    return false;
                }
                if (currentTransaction.getInputsValue() != currentTransaction.getOutputsValue()) {
                    System.out.println("#Inputs are note equal to outputs on Transaction(" + t + ")");
                    return false;
                }

                for (TransactionInput input : currentTransaction.inputs) {
                    tempOutput = tempUTXOs.get(input.transactionOutputId);

                    if (tempOutput == null) {
                        System.out.println("#Referenced input on Transaction(" + t + ") is Missing");
                        return false;
                    }

                    if (input.UTXO.value != tempOutput.value) {
                        System.out.println("#Referenced input Transaction(" + t + ") value is Invalid");
                        return false;
                    }

                    tempUTXOs.remove(input.transactionOutputId);
                }

                for (TransactionOutput output : currentTransaction.outputs) {
                    tempUTXOs.put(output.id, output);
                }

                if (currentTransaction.outputs.get(0).reciepient != currentTransaction.reciepient) {
                    System.out.println("#Transaction(" + t + ") output reciepient is not who it should be");
                    return false;
                }
                if (currentTransaction.outputs.get(1).reciepient != currentTransaction.sender) {
                    System.out.println("#Transaction(" + t + ") output 'change' is not sender.");
                    return false;
                }

            }

        }
        System.out.println("Blockchain is valid");
        return true;
    }

    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }
}
