import React from 'react';
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
import { Ionicons, Feather, MaterialCommunityIcons } from '@expo/vector-icons';
import { useRouter, useLocalSearchParams } from 'expo-router';

export default function ConfirmarTransferencia() {
  const router = useRouter();
  const params = useLocalSearchParams();

  // Valores passados pelas ecrãs anteriores
  const nome = params.nome || 'Rainam Maia Santos Souza';
  const valor = params.valor || 'R$ 0,01';

  const handleEnviar = () => {
    Alert.alert(
      'Sucesso!',
      `Transferência de ${valor} para ${nome} realizada com sucesso!`,
      [
        {
          text: 'OK',
          onPress: () => router.push('/'),
        },
      ]
    );
  };

  return (
    <SafeAreaView style={styles.safeArea}>
      <StatusBar barStyle="dark-content" backgroundColor="#FFFFFF" />

      <View style={styles.container}>
        {/* Header / Voltar */}
        <TouchableOpacity
          style={styles.backButton}
          onPress={() => router.back()}
          activeOpacity={0.7}
        >
          <Ionicons name="chevron-back" size={28} color="#2D2D2D" />
        </TouchableOpacity>

        {/* Título Principal */}
        <Text style={styles.title}>Você vai enviar</Text>

        <ScrollView showsVerticalScrollIndicator={false} contentContainerStyle={styles.scrollContent}>
          {/* Bloco: Valor */}
          <View style={styles.rowItem}>
            <Text style={styles.textoDestaque}>{valor}</Text>
            <TouchableOpacity onPress={() => router.back()} activeOpacity={0.7}>
              <Feather name="edit-2" size={20} color="#00A88F" />
            </TouchableOpacity>
          </View>

          {/* Bloco: Nome do Destinatário */}
          <View style={styles.rowItem}>
            <Text style={styles.textoDestaque}>{nome}</Text>
            <TouchableOpacity onPress={() => router.push('/transferir')} activeOpacity={0.7}>
              <Feather name="edit-2" size={20} color="#00A88F" />
            </TouchableOpacity>
          </View>

          {/* Opção: Detalhes */}
          <TouchableOpacity style={styles.rowOption} activeOpacity={0.7}>
            <Text style={styles.labelOption}>Detalhes</Text>
            <Feather name="chevron-down" size={20} color="#666666" />
          </TouchableOpacity>

          {/* Opção: Quando */}
          <View style={styles.rowOptionWithIcon}>
            <View>
              <Text style={styles.labelOption}>Quando</Text>
              <Text style={styles.valueOption}>Agora</Text>
            </View>
            <TouchableOpacity activeOpacity={0.7}>
              <Feather name="calendar" size={22} color="#00A88F" />
            </TouchableOpacity>
          </View>

          {/* Opção: Mensagem */}
          <View style={styles.rowOptionWithIcon}>
            <Text style={styles.labelOption}>Mensagem</Text>
            <TouchableOpacity activeOpacity={0.7}>
              <Ionicons name="add" size={26} color="#00A88F" />
            </TouchableOpacity>
          </View>
        </ScrollView>

        {/* Rodapé Fixe */}
        <View style={styles.bottomBar}>
          <View>
            <Text style={styles.bottomValorText}>{valor}</Text>
            <Text style={styles.bottomSubtext}>Valor total</Text>
          </View>

          <TouchableOpacity style={styles.btnEnviar} onPress={handleEnviar} activeOpacity={0.85}>
            <Text style={styles.btnEnviarTexto}>Enviar</Text>
          </TouchableOpacity>
        </View>
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
    backgroundColor: '#FFFFFF',
  },
  backButton: {
    paddingHorizontal: 20,
    paddingVertical: 12,
    width: 40,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#000000',
    paddingHorizontal: 20,
    marginTop: 8,
    marginBottom: 24,
  },
  scrollContent: {
    paddingHorizontal: 20,
    paddingBottom: 20,
  },
  rowItem: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingVertical: 18,
    borderBottomWidth: 1,
    borderBottomColor: '#F0F0F0',
  },
  textoDestaque: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#00A88F',
    flex: 1,
    marginRight: 12,
  },
  rowOption: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingVertical: 18,
    borderBottomWidth: 1,
    borderBottomColor: '#F0F0F0',
  },
  rowOptionWithIcon: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingVertical: 18,
    borderBottomWidth: 1,
    borderBottomColor: '#F0F0F0',
  },
  labelOption: {
    fontSize: 14,
    fontWeight: '600',
    color: '#757575',
  },
  valueOption: {
    fontSize: 15,
    fontWeight: 'bold',
    color: '#000000',
    marginTop: 4,
  },
  bottomBar: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingHorizontal: 20,
    paddingVertical: 16,
    borderTopWidth: 1,
    borderTopColor: '#F0F0F0',
    backgroundColor: '#FFFFFF',
  },
  bottomValorText: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#00A88F',
  },
  bottomSubtext: {
    fontSize: 12,
    color: '#757575',
    marginTop: 2,
  },
  btnEnviar: {
    backgroundColor: '#00A88F',
    paddingVertical: 12,
    paddingHorizontal: 32,
    borderRadius: 24,
  },
  btnEnviarTexto: {
    color: '#FFFFFF',
    fontSize: 14,
    fontWeight: 'bold',
  },
});