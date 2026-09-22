import React, { useEffect, useState } from 'react';
import { View, Image, StyleSheet, StatusBar } from 'react-native';
import { Slot } from 'expo-router';

export default function RootLayout() {
  const [appPronta, setAppPronta] = useState(false);

  useEffect(() => {
   
    const timer = setTimeout(() => {
      setAppPronta(true);
    }, 2000);

    return () => clearTimeout(timer);
  }, []);


  if (!appPronta) {
    return (
      <View style={styles.splashContainer}>
        <StatusBar barStyle="light-content" backgroundColor="#00A88F" />
        <Image
          source={require('../assets/images/logo-chiken.png')}
          style={styles.logo}
          resizeMode="contain"
        />
      </View>
    );
  }


  return <Slot />;
}

const styles = StyleSheet.create({
  splashContainer: {
    flex: 1,
    backgroundColor: '#00A88F', // O tom verde exato do seu banco
    justifyContent: 'center',
    alignItems: 'center',
  },
  logo: {
    width: 220,
    height: 220,
  },
});