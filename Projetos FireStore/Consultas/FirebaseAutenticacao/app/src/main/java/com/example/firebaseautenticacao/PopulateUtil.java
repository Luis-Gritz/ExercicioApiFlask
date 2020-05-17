package com.example.firebaseautenticacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

class PopulateUtil {


    public static List<Pessoa> loadPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();

        Pessoa pessoa = new Pessoa();
        pessoa.nome = "Pedro José";
        pessoa.qtde_filhos = 1;
        pessoa.salario = 2500.75;
        pessoa.ativo = false;
        pessoa.pets = Arrays.asList("Bob","Costelinha");
        pessoa.data_aniversario = new GregorianCalendar(1991, Calendar.AUGUST,17).getTime();

        pessoas.add(pessoa);

        pessoa = new Pessoa();
        pessoa.nome = "Ana Maria";
        pessoa.qtde_filhos = 2;
        pessoa.salario = 3500.75;
        pessoa.ativo = true;
        pessoa.pets = Arrays.asList("Greg","Gold");
        pessoa.data_aniversario = new GregorianCalendar(1998, Calendar.JANUARY,20).getTime();

        pessoas.add(pessoa);

        pessoa = new Pessoa();
        pessoa.nome = "Carloos";
        pessoa.qtde_filhos = 7;
        pessoa.salario = 500.75;
        pessoa.ativo = true;
        pessoa.pets = null;
        pessoa.data_aniversario = new GregorianCalendar(2010, Calendar.DECEMBER,25).getTime();

        pessoas.add(pessoa);

        return  pessoas;

    }
}
