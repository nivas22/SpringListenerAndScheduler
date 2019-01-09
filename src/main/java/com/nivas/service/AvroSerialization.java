package com.nivas.service;


import com.nivas.avro.Transaction;
import com.nivas.avro.TransactionListDto;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class AvroSerialization {

    public static byte[] serialize(com.nivas.modal.Transaction transactionDto) throws Exception {
        Transaction transaction = Transaction.newBuilder().setCardNumber(transactionDto.getCardNumber())
                .setAmount(transactionDto.getAmount()).setDate(Math.toIntExact(transactionDto.getDate().getTime() / 1000))
                .setStatus(transactionDto.getStatus()).build();

        byte[] payload;
        DatumWriter<Transaction> datumWriter = new SpecificDatumWriter<>(Transaction.class);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Encoder out = EncoderFactory.get().binaryEncoder(baos, null);
            datumWriter.write(transaction, out);
            out.flush();
            payload = baos.toByteArray();
        } catch (Exception e) {
            System.err.println(e);
            throw e;
        }
        System.out.println(new String(payload));
        System.out.println(payload.length);
        return payload;
    }


    public static byte[] serialize(List<Transaction> transactionList) throws Exception {
        TransactionListDto transactionListDto = TransactionListDto.newBuilder().setTransactionList(transactionList).build();
        byte[] payload;
        DatumWriter<TransactionListDto> datumWriter = new SpecificDatumWriter<>(TransactionListDto.class);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Encoder out = EncoderFactory.get().binaryEncoder(baos, null);
            datumWriter.write(transactionListDto, out);
            out.flush();
            payload = baos.toByteArray();
        } catch (Exception e) {
            System.err.println(e);
            throw e;
        }
        System.out.println(new String(payload));
        System.out.println(payload.length);
        return payload;
    }


    public static TransactionListDto deserialize(byte[] data) throws Exception {
        DatumReader<TransactionListDto> datumReader = new SpecificDatumReader<>(TransactionListDto.class);
        Decoder decoder = DecoderFactory.get().binaryDecoder(data, null);

        TransactionListDto transactionListDto = datumReader.read(null, decoder);
        System.out.println(transactionListDto.getTransactionList().size());
        return transactionListDto;
    }

}
