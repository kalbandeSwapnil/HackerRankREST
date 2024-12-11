package com.rest.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.Integration.sample.TransactionResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    public static int getCount(String userid, String locationId, String netStartId, String netEndId) throws IOException, InterruptedException {

        int page = 1;
        int count = 0;
        int totalPage = 1;

        String uri = "https://jsonmock.hackerrank.com/api/transactions/search?userId=" + userid + "&page=" + page;

        while (page <= totalPage) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(uri))
                    .build();
            HttpClient httpClient = HttpClient.newBuilder().build();

            HttpResponse<InputStream> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
            ObjectMapper objectMapper = new ObjectMapper();

            TransactionResponse transactionResponse = objectMapper.readValue(response.body(), TransactionResponse.class);

            count = count + Math.toIntExact(
                    transactionResponse.getData().stream()
                            .filter(transaction -> transaction.getLocation().getId().equals(locationId))
                            .filter(transaction -> {
                                try {
                                    return checkIpIsInRange(transaction.getIp(), netStartId, netEndId);
                                } catch (UnknownHostException e) {
                                    e.printStackTrace();
                                }
                                return false;
                            })
                            .count());

            System.out.println("Iteration count :" + count);

            totalPage = transactionResponse.getTotal_pages();
            page++;
        }
        return count;
    }

    public static long ipToLong(InetAddress ip) {
        byte[] octets = ip.getAddress();
        long result = 0;
        for (byte octet : octets) {
            result <<= 8;
            result |= octet & 0xff;
        }
        return result;
    }

    public static boolean checkIpIsInRange(String ip, String startIp, String endIp) throws UnknownHostException {
        long ipLo = ipToLong(InetAddress.getByName(startIp));
        long ipHi = ipToLong(InetAddress.getByName(endIp));
        long ipToTest = ipToLong(InetAddress.getByName(ip));

        return ipToTest >= ipLo && ipToTest <= ipHi;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Main.getCount("2", "6", "119.162.205.000", "142.216.23.1");
    }


}





