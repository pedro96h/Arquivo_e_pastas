/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivoepasta;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author pedro
 */
public class ArquivoEPasta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        List<Produto> listaDeProdutos = new ArrayList<>();

        System.out.print("digite o caminho do arquivo : ");
        String path = sc.nextLine();
        File file = new File(path);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line = br.readLine();
            String detalhesDosProdutos[];
            while (line != null) {
                detalhesDosProdutos = line.split(",");
                String nomeDoProduto = detalhesDosProdutos[0].trim();
                double precoDoProduto = Double.parseDouble(detalhesDosProdutos[1].trim());
                int quantidadeDoProduto = Integer.parseInt(detalhesDosProdutos[2].trim());
                listaDeProdutos.add(new Produto(nomeDoProduto, precoDoProduto, quantidadeDoProduto));
                line = br.readLine();
            }
            System.out.print("digite o nome da nova pasta: ");
            String novaPasta = sc.nextLine();

            boolean sucesso = new File(file.getParent()+"\\"+novaPasta).mkdir();
            if (!sucesso) {
                System.out.println("deu ruim para criar a pasta");
            } else {
                System.out.print("digite o nome do novo arquivo: ");
                String novoAquivo = sc.nextLine();
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getParent()+"\\"+novaPasta+"\\"+novoAquivo+".txt"))){
                    for(Produto p : listaDeProdutos){
                        bw.write(p.toString());
                        bw.newLine();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar ler o arquivo");
            System.out.println(e.getMessage());
        }

        sc.close();

    }

}
