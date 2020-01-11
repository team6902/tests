# Teste de componentes

# ToDo

- [ ] Testar Pixy;
- [x] Testar ultrassônico e infravermelho;
- [ ] Testar encoder do motor;
- [ ] Testar NavX;
	- [x] USB;
	- [ ] I2C;
- [ ] Testar duas webcams;

## Testar Pixy

Deixar para outro dia.

## Testar encoder do motor;

Descobrir como utilizar o encoder do motor Neverest Gearmotor.
https://github.com/wpilibsuite/allwpilib/blob/master/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/encoder/Robot.java

## Testar NavX

A NavX está funcionando corretamente no USB, mas o ideal é utilizar ela na I2C para deixar o USB livres.
Tente conectar na expansão MXP da RoboRIo conforme o tutorial (cuidado com as entradas GNC e 5V):
https://pdocs.kauailabs.com/navx-micro/installation/roborio-installation/

Para testar use este código: https://github.com/team6902/tests/tree/master/navx_i2c_0301 e abra o __ShuffleBoard__ para ver se os dados são atualizados.

## Testar duas webcans

Colocar duas webcams e verificar se aparece no __ShuffleBoard__.
Utilizar o código: https://github.com/team6902/tests/tree/master/cameraserver_1101 

