import React, { useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  ScrollView,
  StatusBar,
  Alert,
  Modal,
  TextInput,
  TouchableWithoutFeedback,
} from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { Ionicons, Feather } from '@expo/vector-icons';
import { useRouter } from 'expo-router';

export default function Chave() {
  const router = useRouter();

  // Lista de chaves cadastradas (MOCK)
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

  // Estados do Modal
  const [modalVisible, setModalVisible] = useState(false);
  const [novaChave, setNovaChave] = useState('');

  const fecharTela = () => {
    if (router.canGoBack()) {
      router.back();
    } else {
      router.push('/');
    }
  };

  // Função para cadastrar a nova chave
  const handleSalvarChave = () => {
    if (!novaChave.trim()) {
      Alert.alert('Atenção', 'Por favor, insira o dado para registrar a chave.');
      return;
    }

    const valor = novaChave.trim();
    const ehEmail = valor.includes('@');

    const novaItem = {
      id: Date.now().toString(),
      tipo: ehEmail ? 'E-mail' : 'Telefone / CPF',
      valor: valor,
      tipoIcone: ehEmail ? 'mail' : 'phone',
    };

    setChaves([novaItem, ...chaves]);
    setNovaChave('');
    setModalVisible(false);
  };

  const handleOpcoesChave = (chave) => {
    Alert.alert(
      `Gerenciar ${chave.tipo}`,
      `Chave: ${chave.valor}`,
      [
        { text: 'Copiar Chave', onPress: () => {} },
        { 
          text: 'Excluir Chave', 
          style: 'destructive', 
          onPress: () => {
            setChaves(chaves.filter((c) => c.id !== chave.id));
          } 
        },
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

        {/* Linha Registrar Chave (+) */}
        <TouchableOpacity 
          style={styles.registrarRow} 
          onPress={() => setModalVisible(true)} 
          activeOpacity={0.7}
        >
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

      {/* MODAL BOTTOM SHEET DE REGISTRAR CHAVE */}
      <Modal
        animationType="slide"
        transparent={true}
        visible={modalVisible}
        onRequestClose={() => setModalVisible(false)}
      >
        <TouchableWithoutFeedback onPress={() => setModalVisible(false)}>
          <View style={styles.modalOverlay}>
            <TouchableWithoutFeedback>
              <View style={styles.modalContent}>
                {/* Botão Voltar (<) */}
                <TouchableOpacity 
                  style={styles.modalBackButton} 
                  onPress={() => setModalVisible(false)}
                  activeOpacity={0.7}
                >
                  <Ionicons name="chevron-back" size={26} color="#2D2D2D" />
                </TouchableOpacity>

                {/* Título do Modal */}
                <Text style={styles.modalTitle}>Registrar chave</Text>

                {/* Subtítulo */}
                <Text style={styles.modalSubTitle}>Insira o dado para registrar a chave</Text>

                {/* Campo de Texto */}
                <TextInput
                  style={styles.input}
                  placeholder="Telefone, CPF/CNPJ ou chave Pix"
                  placeholderTextColor="#B0B0B0"
                  value={novaChave}
                  onChangeText={setNovaChave}
                  autoCapitalize="none"
                />

                {/* Botão Registrar no canto direito */}
                <View style={styles.buttonContainer}>
                  <TouchableOpacity 
                    style={styles.btnRegistrar} 
                    onPress={handleSalvarChave}
                    activeOpacity={0.8}
                  >
                    <Text style={styles.btnRegistrarTexto}>Registrar</Text>
                  </TouchableOpacity>
                </View>
              </View>
            </TouchableWithoutFeedback>
          </View>
        </TouchableWithoutFeedback>
      </Modal>
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

  /* Estilos do Modal */
  modalOverlay: {
    flex: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.4)',
    justifyContent: 'flex-end',
  },
  modalContent: {
    backgroundColor: '#FFFFFF',
    borderTopLeftRadius: 28,
    borderTopRightRadius: 28,
    paddingHorizontal: 24,
    paddingTop: 16,
    paddingBottom: 40,
    minHeight: '65%',
  },
  modalBackButton: {
    width: 40,
    height: 40,
    justifyContent: 'center',
    marginBottom: 8,
  },
  modalTitle: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#000000',
    marginBottom: 32,
  },
  modalSubTitle: {
    fontSize: 14,
    fontWeight: '600',
    color: '#000000',
    marginBottom: 12,
  },
  input: {
    borderBottomWidth: 1,
    borderBottomColor: '#E0E0E0',
    fontSize: 16,
    paddingVertical: 8,
    color: '#000000',
    marginBottom: 28,
  },
  buttonContainer: {
    alignItems: 'flex-end',
  },
  btnRegistrar: {
    backgroundColor: '#00A88F',
    paddingVertical: 10,
    paddingHorizontal: 24,
    borderRadius: 20,
  },
  btnRegistrarTexto: {
    color: '#FFFFFF',
    fontSize: 15,
    fontWeight: 'bold',
  },
});