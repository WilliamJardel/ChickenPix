import React from 'react';
import {
  Modal,
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  TouchableWithoutFeedback,
} from 'react-native';
import { Ionicons, Feather } from '@expo/vector-icons';
import { useRouter } from 'expo-router';

export default function PerfilModal({ visible, onClose, usuario }) {
  const router = useRouter();

  const handleSair = () => {
    onClose();
    // Exemplo: redirecionar para a tela de login se necessário
    // router.replace('/login');
  };

  return (
    <Modal
      animationType="slide"
      transparent={true}
      visible={visible}
      onRequestClose={onClose}
    >
      <TouchableWithoutFeedback onPress={onClose}>
        <View style={styles.overlay}>
          <TouchableWithoutFeedback>
            <View style={styles.modalContainer}>
              {/* Cabeçalho: Botão Fechar (X) e Notificação (Sino) */}
              <View style={styles.header}>
                <TouchableOpacity onPress={onClose} activeOpacity={0.7}>
                  <Ionicons name="close" size={26} color="#2D2D2D" />
                </TouchableOpacity>
                <TouchableOpacity activeOpacity={0.7}>
                  <Ionicons name="notifications-outline" size={24} color="#2D2D2D" />
                </TouchableOpacity>
              </View>

              {/* Informações do Perfil */}
              <View style={styles.profileSection}>
                <View style={styles.avatarCircle}>
                  <Feather name="user" size={30} color="#2D2D2D" />
                </View>
                <View style={styles.profileTextContainer}>
                  <Text style={styles.nomeText}>
                    {usuario?.nome || 'Adelia holanda'}
                  </Text>
                  <Text style={styles.contaText}>
                    Agência {usuario?.agencia || '0001'} - Conta {usuario?.conta || '22323231'} -{' '}
                    <Text style={styles.maisText}>Mais</Text>
                  </Text>
                </View>
              </View>

              {/* Seção Contas */}
              <Text style={styles.sectionTitle}>Contas</Text>

              {/* Botão Sair do aplicativo */}
              <TouchableOpacity style={styles.optionRow} onPress={handleSair} activeOpacity={0.7}>
                <View style={styles.optionLeft}>
                  <View style={styles.iconCircle}>
                    <Feather name="arrow-left" size={20} color="#2D2D2D" />
                  </View>
                  <Text style={styles.optionText}>Sair do aplicativo</Text>
                </View>
                <Feather name="chevron-right" size={20} color="#2D2D2D" />
              </TouchableOpacity>
            </View>
          </TouchableWithoutFeedback>
        </View>
      </TouchableWithoutFeedback>
    </Modal>
  );
}

const styles = StyleSheet.create({
  overlay: {
    flex: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.4)',
    justifyContent: 'flex-end',
  },
  modalContainer: {
    backgroundColor: '#FFFFFF',
    borderTopLeftRadius: 24,
    borderTopRightRadius: 24,
    paddingHorizontal: 20,
    paddingTop: 16,
    paddingBottom: 40,
    minHeight: '55%',
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 20,
  },
  profileSection: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 24,
  },
  avatarCircle: {
    width: 60,
    height: 60,
    borderRadius: 30,
    backgroundColor: '#BCEBE3',
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 14,
  },
  profileTextContainer: {
    flex: 1,
  },
  nomeText: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#000000',
    marginBottom: 4,
  },
  contaText: {
    fontSize: 13,
    color: '#555555',
  },
  maisText: {
    color: '#00A88F',
    fontWeight: 'bold',
  },
  sectionTitle: {
    fontSize: 14,
    fontWeight: '500',
    color: '#8E8E93',
    marginBottom: 12,
    marginTop: 8,
  },
  optionRow: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    paddingVertical: 12,
    borderBottomWidth: 1,
    borderBottomColor: '#F0F0F0',
  },
  optionLeft: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  iconCircle: {
    width: 42,
    height: 42,
    borderRadius: 21,
    backgroundColor: '#E5E5E5',
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 14,
  },
  optionText: {
    fontSize: 15,
    fontWeight: '600',
    color: '#000000',
  },
});