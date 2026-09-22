import React, { useState } from 'react';
import { StyleSheet, StatusBar, ScrollView, View } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { useRouter } from 'expo-router';

import Header from '../src/components/Header';
import CardSaldo from '../src/components/CardSaldo';
import AcessoRapido from '../src/components/AcessoRapido';
import HistoricoTransferencia from '../src/components/HistoricoTransferencia';
import BotaoPix from '../src/components/BotaoPix';
import PerfilModal from '../src/components/PerfilModal';

export default function Home() {
  const router = useRouter();
  const [perfilVisible, setPerfilVisible] = useState(false);

  return (
    <SafeAreaView style={styles.container}>
      <StatusBar barStyle="light-content" backgroundColor="#00A88F" />

      {/* Conteúdo com scroll para evitar cortes em telas menores */}
      <ScrollView 
        showsVerticalScrollIndicator={false}
        contentContainerStyle={styles.scrollContent}
      >
        <Header 
          nome="Pedro" 
          onPressPerfil={() => setPerfilVisible(true)}
          onPressMenu={() => setPerfilVisible(true)}
        />
        
        <CardSaldo 
          saldo="R$ 67,00" 
          onPress={() => alert('Abrir detalhes do saldo')} 
        />
        
        <AcessoRapido 
          onPix={() => router.push('/transferir')}
          onCofre={() => alert('Navegar para Cofre')}
          onChaves={() => router.push('/chave')}
        />
        
        <HistoricoTransferencia />
      </ScrollView>

      {/* Botão Pix flutuante no fundo */}
      <BotaoPix />

      {/* Modal de Perfil acionado ao clicar no Avatar/Header */}
      <PerfilModal
        visible={perfilVisible}
        onClose={() => setPerfilVisible(false)}
        usuario={{
          nome: 'Pedro',
          agencia: '0001',
          conta: '22323231',
        }}
      />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FFFFFF',
  },
  scrollContent: {
    paddingBottom: 90, // Espaço extra para o BotaoPix não cobrir o conteúdo final
  },
});