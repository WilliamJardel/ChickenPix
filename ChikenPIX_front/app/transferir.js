import React, { useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TextInput,
  TouchableOpacity,
  ScrollView,
  StatusBar,
} from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { Ionicons, Feather } from '@expo/vector-icons';
import { useRouter } from 'expo-router';

export default function Transferir() {
  const router = useRouter();
  const [busca, setBusca] = useState('');

  // Dados locais simulados (sem necessidade de API)
  const recentes = [
    { id: '1', nome: 'Rainam', chave: 'rainam@pix.com' },
    { id: '2', nome: 'David', chave: 'david@pix.com' },
    { id: '3', nome: 'Diano', chave: 'diano@pix.com' },
  ];

  const todosContatos = [
    { id: '1', nome: 'Rayn Gosling', inicial: 'RG', chave: 'rayn@pix.com' },
    { id: '2', nome: 'Rayn Gosling', inicial: 'RG', chave: 'rayn2@pix.com' },
    { id: '3', nome: 'Rayn Gosling', inicial: 'RG', chave: 'rayn3@pix.com' },
  ];

  const fecharTela = () => {
    if (router.canGoBack()) {
      router.back();
    } else {
      router.push('/');
    }
  };

  const selecionarContato = (contato) => {
    router.push({
      pathname: '/valor-transferencia',
      params: {
        destinatarioId: contato.id,
        nome: contato.nome,
        chave: contato.chave || busca,
      },
    });
  };

  // Filtra os contatos em tempo real conforme a pesquisa
  const contatosFiltrados = todosContatos.filter((contato) =>
    contato.nome.toLowerCase().includes(busca.toLowerCase()) ||
    (contato.chave && contato.chave.toLowerCase().includes(busca.toLowerCase()))
  );

  return (
    <SafeAreaView style={styles.safeArea}>
      <StatusBar barStyle="dark-content" backgroundColor="#FFFFFF" />

      <View style={styles.container}>
        {/* Botão Fechar (X) */}
        <TouchableOpacity style={styles.closeButton} onPress={fecharTela} activeOpacity={0.7}>
          <Ionicons name="close" size={28} color="#2D2D2D" />
        </TouchableOpacity>

        {/* Título */}
        <Text style={styles.title}>Para quem você quer transferir?</Text>

        <ScrollView showsVerticalScrollIndicator={false}>
          {/* Campo de Busca */}
          <View style={styles.inputContainer}>
            <Text style={styles.inputLabel}>Insira o dado de quem vai receber</Text>
            <TextInput
              style={styles.input}
              placeholder="Nome, telefone, CPF/CNPJ ou chave Pix"
              placeholderTextColor="#A0A0A0"
              value={busca}
              onChangeText={setBusca}
              onSubmitEditing={() => selecionarContato({ id: '0', nome: busca, chave: busca })}
              returnKeyType="next"
            />
          </View>

          {/* Seção: Você pagou recentemente */}
          <View style={styles.secao}>
            <Text style={styles.secaoTitulo}>Você pagou recentemente</Text>

            <View style={styles.recentesRow}>
              {recentes.map((item) => (
                <TouchableOpacity
                  key={item.id}
                  style={styles.recenteCard}
                  onPress={() => selecionarContato(item)}
                  activeOpacity={0.7}
                >
                  <View style={styles.circuloRecente}>
                    <Feather name="user" size={30} color="#000000" />
                  </View>
                  <Text style={styles.nomeRecente} numberOfLines={1}>{item.nome}</Text>
                </TouchableOpacity>
              ))}
            </View>
          </View>

          {/* Seção: Todos os seus contatos */}
          <View style={styles.secao}>
            <Text style={styles.secaoTitulo}>Todos os seus contatos</Text>

            {contatosFiltrados.map((item, index) => (
              <TouchableOpacity
                key={`${item.id}-${index}`}
                style={styles.contatoRow}
                onPress={() => selecionarContato(item)}
                activeOpacity={0.7}
              >
                <View style={styles.circuloContato}>
                  <Text style={styles.inicialTexto}>
                    {item.inicial || item.nome.substring(0, 2).toUpperCase()}
                  </Text>
                </View>
                <Text style={styles.nomeContato}>{item.nome}</Text>
              </TouchableOpacity>
            ))}
          </View>
        </ScrollView>
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  safeArea: {
    flex: 1,
    backgroundColor: '#FFFFFF',
  },
  container: {
    flex: 1,
    paddingHorizontal: 20,
    backgroundColor: '#FFFFFF',
  },
  closeButton: {
    paddingTop: 12,
    paddingBottom: 12,
    width: 40,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#000000',
    marginBottom: 24,
    lineHeight: 30,
  },
  inputContainer: {
    marginBottom: 28,
  },
  inputLabel: {
    fontSize: 14,
    fontWeight: '600',
    color: '#2D2D2D',
    marginBottom: 8,
  },
  input: {
    fontSize: 15,
    color: '#000000',
    borderBottomWidth: 1,
    borderBottomColor: '#E5E5E5',
    paddingVertical: 8,
  },
  secao: {
    marginBottom: 28,
  },
  secaoTitulo: {
    fontSize: 14,
    fontWeight: '600',
    color: '#2D2D2D',
    marginBottom: 16,
  },
  recentesRow: {
    flexDirection: 'row',
    justifyContent: 'flex-start',
    gap: 20,
  },
  recenteCard: {
    alignItems: 'center',
    width: 72,
  },
  circuloRecente: {
    width: 68,
    height: 68,
    borderRadius: 34,
    backgroundColor: '#BCEBE3',
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 8,
  },
  nomeRecente: {
    fontSize: 14,
    fontWeight: '600',
    color: '#000000',
    textAlign: 'center',
  },
  contatoRow: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 14,
    borderBottomWidth: 1,
    borderBottomColor: '#F0F0F0',
  },
  circuloContato: {
    width: 48,
    height: 48,
    borderRadius: 24,
    backgroundColor: '#BCEBE3',
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 16,
  },
  inicialTexto: {
    fontSize: 14,
    fontWeight: 'bold',
    color: '#000000',
  },
  nomeContato: {
    fontSize: 15,
    fontWeight: '600',
    color: '#000000',
  },
});