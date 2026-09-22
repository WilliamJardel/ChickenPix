import React from 'react';
import { SafeAreaView, StyleSheet, StatusBar, View } from 'react-native';
import Header from '../src/components/Header';
import CardSaldo from '../src/components/CardSaldo';
import AcessoRapido from '../src/components/AcessoRapido';
import HistoricoTransferencia from '../src/components/HistoricoTransferencia';
import BotaoPix from '../src/components/BotaoPix';

export default function Home() {
  return (
    <SafeAreaView style={styles.container}>
      <StatusBar barStyle="light-content" backgroundColor="#00A88F" />
      
      <Header 
        nome="Pedro" 
        onPressPerfil={() => alert('Abrir perfil')}
        onPressMenu={() => alert('Abrir menu')}
      />
      <CardSaldo 
        saldo="R$ 67,00" 
        onPress={() => alert('Abrir detalhes do saldo')} 
      />
      <AcessoRapido 
          onPix={() => alert('Navegar para Pix')}
          onCofre={() => alert('Navegar para Cofre')}
          onChaves={() => alert('Navegar para Chaves')}
        />
        <HistoricoTransferencia />
        <BotaoPix />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FFFFFF',
  },
});