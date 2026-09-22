import React, { useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  ScrollView,
  StatusBar,
  Alert,
} from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { Ionicons, Feather } from '@expo/vector-icons';
import { useRouter } from 'expo-router';

export default function Chave() {
  const router = useRouter();

  // Dados das chaves cadastradas (MOCK)
  const [chaves, setChaves] = useState([
    {
      id: '1',
      tipo: 'E-mail',
      valor: 'adeliaholanda@gmail.com',
      tipoIcone: 'mail',
    },
    {
      id: '2',
      tipo: 'Telefone',
      valor: 'adeliaholanda@gmail.com',
      tipoIcone: 'phone',
    },
  ]);

  const fecharTela = () => {
    if (router.canGoBack()) {
      router.back();
    } else {
      router.push('/');
    }
  };

  const handleRegistrarChave = () => {
    Alert.alert('Registrar chave', 'Redirecionar para o fluxo de cadastro de nova chave Pix.');
  };

  const handleOpcoesChave = (chave) => {
    Alert.alert(
      `Gerenciar ${chave.tipo}`,
      `Chave: ${chave.valor}`,
      [
        { text: 'Copiar Chave', onPress: () => {} },
        { text: 'Excluir Chave', style: 'destructive', onPress: () => {} },
        { text: 'Cancelar', style: 'cancel' },
      ]
    );
  };

  return (
    <SafeAreaView style={styles.safeArea}>
      <StatusBar barStyle="dark-content" backgroundColor="#FFFFFF" />

      <View style={styles.container}>
        {/* Botão Voltar (<) */}
        <TouchableOpacity style={styles.backButton} onPress={fecharTela} activeOpacity={0.7}>
          <Ionicons name="chevron-back" size={28} color="#2D2D2D" />
        </TouchableOpacity>

        {/* Título Principal */}
        <Text style={styles.title}>Minhas chaves</Text>

        {/* Linha Registrar Chave */}
        <TouchableOpacity style={styles.registrarRow} onPress={handleRegistrarChave} activeOpacity={0.7}>
          <Text style={styles.registrarTexto}>Registrar chave</Text>
          <Ionicons name="add" size={26} color="#00A88F" />
        </TouchableOpacity>

        {/* Linha Divisória */}
        <View style={styles.divider} />

        {/* Subtítulo: Chaves cadastradas */}
        <Text style={styles.sectionTitle}>Chaves cadastradas</Text>

        {/* Lista de Chaves */}
        <ScrollView showsVerticalScrollIndicator={false}>
          {chaves.map((item) => (
            <View key={item.id} style={styles.chaveItem}>
              {/* Ícone da Chave */}
              <View style={styles.iconContainer}>
                {item.tipoIcone === 'mail' ? (
                  <Feather name="mail" size={22} color="#000000" />
                ) : (
                  <Feather name="phone" size={22} color="#000000" />
                )}
              </View>

              {/* Informações da Chave */}
              <View style={styles.infoContainer}>
                <Text style={styles.tipoTexto}>{item.tipo}</Text>
                <Text style={styles.valorTexto}>{item.valor}</Text>
              </View>

              {/* Menu 3 Pontos (...) */}
              <TouchableOpacity
                style={styles.menuButton}
                onPress={() => handleOpcoesChave(item)}
                activeOpacity={0.7}
              >
                <Ionicons name="ellipsis-vertical" size={20} color="#000000" />
              </TouchableOpacity>
            </View>
          ))}
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
  backButton: {
    paddingVertical: 12,
    width: 40,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#000000',
    marginTop: 8,
    marginBottom: 28,
  },
  registrarRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingVertical: 12,
  },
  registrarTexto: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#00A88F',
  },
  divider: {
    height: 1,
    backgroundColor: '#F0F0F0',
    marginTop: 16,
    marginBottom: 24,
  },
  sectionTitle: {
    fontSize: 14,
    fontWeight: '600',
    color: '#8E8E93',
    marginBottom: 16,
  },
  chaveItem: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 14,
  },
  iconContainer: {
    width: 32,
    alignItems: 'flex-start',
    justifyContent: 'center',
  },
  infoContainer: {
    flex: 1,
  },
  tipoTexto: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#000000',
    marginBottom: 2,
  },
  valorTexto: {
    fontSize: 14,
    color: '#8E8E93',
  },
  menuButton: {
    padding: 8,
  },
});