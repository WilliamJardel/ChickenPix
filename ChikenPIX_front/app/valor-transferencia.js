import React, { useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TextInput,
  TouchableOpacity,
  StatusBar,
  KeyboardAvoidingView,
  Platform,
} from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { Ionicons } from '@expo/vector-icons';
import { useRouter, useLocalSearchParams } from 'expo-router';

export default function ValorTransferencia() {
  const router = useRouter();
  const params = useLocalSearchParams();

  // Nome do destinatário vindo do ecrã anterior (com fallback para o exemplo da imagem)
  const nomeDestinatario = params.nome || 'Rainam Maia Santos Souza';

  // Valor bruto em centavos (inicia em 1 centavo = "0,01")
  const [valorRaw, setValorRaw] = useState('1');

  // Formata os dígitos inteiros para moeda (Ex: "1" -> "R$ 0,01", "1500" -> "R$ 15,00")
  const formatarMoeda = (valorDigits) => {
    const apenasNumeros = valorDigits.replace(/\D/g, '');
    if (!apenasNumeros) return 'R$ 0,00';
    const numero = parseFloat(apenasNumeros) / 100;
    return numero.toLocaleString('pt-BR', {
      style: 'currency',
      currency: 'BRL',
    });
  };

  const handleTextChange = (text) => {
    const apenasNumeros = text.replace(/\D/g, '');
    setValorRaw(apenasNumeros);
  };

  const avançarParaConfirmacao = () => {
    const valorFormatado = formatarMoeda(valorRaw);
    router.push({
      pathname: '/confirmar-transferencia',
      params: {
        ...params,
        nome: nomeDestinatario,
        valor: valorFormatado,
      },
    });
  };

  return (
    <SafeAreaView style={styles.safeArea}>
      <StatusBar barStyle="dark-content" backgroundColor="#FFFFFF" />

      <KeyboardAvoidingView
        style={styles.container}
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
      >
        {/* Botão Voltar */}
        <TouchableOpacity
          style={styles.backButton}
          onPress={() => router.back()}
          activeOpacity={0.7}
        >
          <Ionicons name="chevron-back" size={28} color="#2D2D2D" />
        </TouchableOpacity>

        <View style={styles.content}>
          {/* Destinatário */}
          <Text style={styles.labelTransferir}>Transferir para</Text>
          <Text style={styles.nomeDestinatario}>{nomeDestinatario}</Text>

          {/* Campo do Valor */}
          <View style={styles.valorContainer}>
            <Text style={styles.labelValor}>Valor</Text>
            <TextInput
              style={styles.inputValor}
              value={formatarMoeda(valorRaw)}
              onChangeText={handleTextChange}
              keyboardType="numeric"
              autoFocus={true}
              onSubmitEditing={avançarParaConfirmacao}
              returnKeyType="next"
            />
          </View>
        </View>
      </KeyboardAvoidingView>
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
  },
  backButton: {
    paddingVertical: 12,
    width: 40,
  },
  content: {
    flex: 1,
    paddingTop: 16,
  },
  labelTransferir: {
    fontSize: 14,
    color: '#333333',
    marginBottom: 4,
  },
  nomeDestinatario: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#000000',
    marginBottom: 36,
  },
  valorContainer: {
    borderBottomWidth: 1,
    borderBottomColor: '#E5E5E5',
    paddingBottom: 8,
  },
  labelValor: {
    fontSize: 14,
    fontWeight: '500',
    color: '#333333',
    marginBottom: 8,
  },
  inputValor: {
    fontSize: 22,
    fontWeight: 'bold',
    color: '#000000',
  },
});