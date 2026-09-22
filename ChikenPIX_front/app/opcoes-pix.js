import React from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  ScrollView,
  SafeAreaView,
  StatusBar,
} from 'react-native';
import { MaterialCommunityIcons, Ionicons } from '@expo/vector-icons';
import { useRouter } from 'expo-router';

export default function OpcoesPix() {
  const router = useRouter();

  const fecharTela = () => {
    if (router.canGoBack()) {
      router.back();
    } else {
      router.push('/');
    }
  };

  return (
    <SafeAreaView style={styles.safeArea}>
      <StatusBar barStyle="dark-content" backgroundColor="#FFFFFF" />

      <View style={styles.container}>
        {/* Botão Fechar (X) */}
        <TouchableOpacity style={styles.closeButton} onPress={fecharTela} activeOpacity={0.7}>
          <Ionicons name="close" size={28} color="#2D2D2D" />
        </TouchableOpacity>

        {/* Título da Tela */}
        <Text style={styles.title}>Opções Pix</Text>

        <ScrollView showsVerticalScrollIndicator={false}>
          {/* Ações Principais (Círculos) */}
          <View style={styles.gridAcoes}>
            {/* Transferir */}
            <TouchableOpacity style={styles.itemGrid} activeOpacity={0.7}>
              <View style={styles.circulo}>
                <MaterialCommunityIcons name="cash-multiple" size={30} color="#000000" />
              </View>
              <Text style={styles.labelGrid}>Transferir</Text>
            </TouchableOpacity>

            {/* Pix Agendado */}
            <TouchableOpacity style={styles.itemGrid} activeOpacity={0.7}>
              <View style={styles.circulo}>
                <MaterialCommunityIcons name="calendar-clock-outline" size={30} color="#000000" />
              </View>
              <Text style={styles.labelGrid}>Pix Agendado</Text>
            </TouchableOpacity>

            {/* Extrato */}
            <TouchableOpacity style={styles.itemGrid} activeOpacity={0.7}>
              <View style={styles.circulo}>
                <MaterialCommunityIcons name="file-document-outline" size={30} color="#000000" />
              </View>
              <Text style={styles.labelGrid}>Extrato</Text>
            </TouchableOpacity>
          </View>

          {/* Seção: Preferências */}
          <View style={styles.secao}>
            <Text style={styles.secaoTitulo}>Preferências</Text>

            <TouchableOpacity style={styles.opcaoRow} activeOpacity={0.6}>
              <View style={styles.opcaoEsquerda}>
                <MaterialCommunityIcons name="key-outline" size={24} color="#000000" />
                <Text style={styles.opcaoTexto}>Gerenciar chaves</Text>
              </View>
              <Ionicons name="chevron-forward" size={20} color="#000000" />
            </TouchableOpacity>

            <TouchableOpacity style={styles.opcaoRow} activeOpacity={0.6}>
              <View style={styles.opcaoEsquerda}>
                <MaterialCommunityIcons name="tune-variant" size={24} color="#000000" />
                <Text style={styles.opcaoTexto}>Meus limites</Text>
              </View>
              <Ionicons name="chevron-forward" size={20} color="#000000" />
            </TouchableOpacity>

            <TouchableOpacity style={styles.opcaoRowSemBorda} activeOpacity={0.6}>
              <View style={styles.opcaoEsquerda}>
                <MaterialCommunityIcons name="bell-outline" size={24} color="#000000" />
                <Text style={styles.opcaoTexto}>Notificações Pix</Text>
              </View>
              <Ionicons name="chevron-forward" size={20} color="#000000" />
            </TouchableOpacity>
          </View>

          <View style={styles.divisorBloco} />

          {/* Seção: Suporte */}
          <View style={styles.secao}>
            <Text style={styles.secaoTitulo}>Suporte</Text>

            <TouchableOpacity style={styles.opcaoRowSemBorda} activeOpacity={0.6}>
              <View style={styles.opcaoEsquerda}>
                <MaterialCommunityIcons name="alert-octagon-outline" size={24} color="#000000" />
                <Text style={styles.opcaoTexto}>Contestação de transações Pix</Text>
              </View>
              <Ionicons name="chevron-forward" size={20} color="#000000" />
            </TouchableOpacity>
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
    paddingBottom: 16,
    width: 40,
  },
  title: {
    fontSize: 26,
    fontWeight: 'bold',
    color: '#000000',
    marginBottom: 24,
  },
  gridAcoes: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    alignItems: 'center',
    marginBottom: 32,
  },
  itemGrid: {
    alignItems: 'center',
    width: 90,
  },
  circulo: {
    width: 72,
    height: 72,
    borderRadius: 36,
    backgroundColor: '#BCEBE3', // Tom verde-água suave do projeto
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 8,
  },
  labelGrid: {
    fontSize: 13,
    fontWeight: 'bold',
    color: '#000000',
    textAlign: 'center',
  },
  secao: {
    paddingVertical: 8,
  },
  secaoTitulo: {
    fontSize: 16,
    fontWeight: '600',
    color: '#8A8A8E',
    marginBottom: 12,
  },
  opcaoRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingVertical: 16,
    borderBottomWidth: 1,
    borderBottomColor: '#F0F0F0',
  },
  opcaoRowSemBorda: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingVertical: 16,
  },
  opcaoEsquerda: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  opcaoTexto: {
    fontSize: 15,
    fontWeight: '600',
    color: '#000000',
    marginLeft: 16,
  },
  divisorBloco: {
    height: 1,
    backgroundColor: '#F0F0F0',
    marginVertical: 8,
  },
});