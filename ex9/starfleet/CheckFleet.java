package Exercises.ex9.starfleet;

import Exercises.ex8.wordsRank.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class CheckFleet {

    public static void main(String[] args) {
        String i1 = "*** STARFLEET COMMAND OFFICIAL REPORT ***\n" +
                "\n" +
                "Fleet ships sorted by fire power and commission year:\n" +
                "Fighter\n" +
                "\tName=USS Defiant\n" +
                "\tCommissionYear=2423\n" +
                "\tMaximalSpeed=6.0\n" +
                "\tFirePower=290\n" +
                "\tCrewMembers=130\n" +
                "\tAnnualMaintenanceCost=8990\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=110], Weapon [name=Quantum Torpedoes, firePower=120, annualMaintenanceCost=100], Weapon [name=TAU Phasers, firePower=150, annualMaintenanceCost=280]]\n" +
                "Bomber\n" +
                "\tName=USS Yamaguchi\n" +
                "\tCommissionYear=2416\n" +
                "\tMaximalSpeed=9.9\n" +
                "\tFirePower=140\n" +
                "\tCrewMembers=233\n" +
                "\tAnnualMaintenanceCost=5185\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=110], Weapon [name=Photon Torpedoes, firePower=120, annualMaintenanceCost=260]]\n" +
                "\tNumberOfTechnicians=5\n" +
                "ColonialViper\n" +
                "\tName=Viper1\n" +
                "\tCommissionYear=2451\n" +
                "\tMaximalSpeed=7.2\n" +
                "\tFirePower=50\n" +
                "\tCrewMembers=1\n" +
                "\tAnnualMaintenanceCost=8500\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\n" +
                "ColonialViper\n" +
                "\tName=Viper2\n" +
                "\tCommissionYear=2451\n" +
                "\tMaximalSpeed=7.2\n" +
                "\tFirePower=50\n" +
                "\tCrewMembers=1\n" +
                "\tAnnualMaintenanceCost=8500\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\n" +
                "StealthCruiser\n" +
                "\tName=USS Galaxy\n" +
                "\tCommissionYear=2370\n" +
                "\tMaximalSpeed=9.0\n" +
                "\tFirePower=50\n" +
                "\tCrewMembers=4\n" +
                "\tAnnualMaintenanceCost=12050\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\n" +
                "CylonRaider\n" +
                "\tName=Raider 1\n" +
                "\tCommissionYear=2056\n" +
                "\tMaximalSpeed=3.5\n" +
                "\tFirePower=50\n" +
                "\tCrewMembers=1\n" +
                "\tAnnualMaintenanceCost=8600\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\n" +
                "StealthCruiser\n" +
                "\tName=USS Odyssey\n" +
                "\tCommissionYear=2419\n" +
                "\tMaximalSpeed=9.0\n" +
                "\tFirePower=20\n" +
                "\tCrewMembers=4\n" +
                "\tAnnualMaintenanceCost=11750\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100]]\n" +
                "StealthCruiser\n" +
                "\tName=USS Amsterdamer\n" +
                "\tCommissionYear=2410\n" +
                "\tMaximalSpeed=9.2\n" +
                "\tFirePower=20\n" +
                "\tCrewMembers=3\n" +
                "\tAnnualMaintenanceCost=11950\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100]]\n" +
                "TransportShip\n" +
                "\tName=USS Lantree\n" +
                "\tCommissionYear=2457\n" +
                "\tMaximalSpeed=5.1\n" +
                "\tFirePower=10\n" +
                "\tCrewMembers=9\n" +
                "\tAnnualMaintenanceCost=48000\n" +
                "\tCargoCapacity=3000\n" +
                "\tPassengerCapacity=10000\n" +
                "TransportShip\n" +
                "\tName=USS Astral Queen\n" +
                "\tCommissionYear=2396\n" +
                "\tMaximalSpeed=5.1\n" +
                "\tFirePower=10\n" +
                "\tCrewMembers=23\n" +
                "\tAnnualMaintenanceCost=28000\n" +
                "\tCargoCapacity=2000\n" +
                "\tPassengerCapacity=5000\n" +
                "\n" +
                "Ship counts by type:\n" +
                "\t1\tBomber\n" +
                "\t2\tColonialViper\n" +
                "\t1\tCylonRaider\n" +
                "\t1\tFighter\n" +
                "\t3\tStealthCruiser\n" +
                "\t2\tTransportShip\n" +
                "\n" +
                "Weapon types:\n" +
                "\tEvaporator\n" +
                "\tLaser Cannons\n" +
                "\tPhoton Torpedoes\n" +
                "\tQuantum Torpedoes\n" +
                "\tTAU Phasers\n" +
                "\n" +
                "Highest ranking officer per ship:\n" +
                "\tCaptain\tArcher #171\tUSS Amsterdamer\n" +
                "\tCaptain\tArcher #27\tUSS Lantree\n" +
                "\tLieutenant\tCara Thrace\tViper2\n" +
                "\tCaptain\tData #167\tUSS Odyssey\n" +
                "\tCaptain\tFitzRoy #163\tUSS Galaxy\n" +
                "\tCaptain\tLee Adama\tViper1\n" +
                "\tCaptain\tNemo #9\tUSS Astral Queen\n" +
                "\tCaptain\tPicard #194\tUSS Yamaguchi\n" +
                "\tCaptain\tSisko #52\tUSS Defiant\n" +
                "\n" +
                "Officer ranks sorted ascendingly by popularity:\n" +
                "\t7\tLieutenantCommander\n" +
                "\t7\tCommander\n" +
                "\t8\tCaptain\n" +
                "\t15\tLieutenant\n" +
                "\t22\tEnsign\n" +
                "\n" +
                "Fleet Totals:\n" +
                "\tTotal fleet crew members:\t\t\t409\n" +
                "\tAverage age of fleet officers:\t\t\t42.66\n" +
                "\tTotal annual maintenance cost:\t\t\t151525";
        String i2 = "*** STARFLEET COMMAND OFFICIAL REPORT ***\n" +
                "\n" +
                "Fleet ships sorted by fire power and commission year:\n" +
                "Fighter\n" +
                "\tName=USS Defiant\n" +
                "\tCommissionYear=2423\n" +
                "\tMaximalSpeed=6.0\n" +
                "\tFirePower=290\n" +
                "\tCrewMembers=130\n" +
                "\tAnnualMaintenanceCost=8990\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=110], Weapon [name=Quantum Torpedoes, firePower=120, annualMaintenanceCost=100], Weapon [name=TAU Phasers, firePower=150, annualMaintenanceCost=280]]\n" +
                "Bomber\n" +
                "\tName=USS Yamaguchi \n" +
                "\tCommissionYear=2416\n" +
                "\tMaximalSpeed=9.9\n" +
                "\tFirePower=140\n" +
                "\tCrewMembers=233\n" +
                "\tAnnualMaintenanceCost=5185\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=110], Weapon [name=Photon Torpedoes, firePower=120, annualMaintenanceCost=260]]\n" +
                "\tNumberOfTechnicians=5\n" +
                "ColonialViper\n" +
                "\tName=Viper1\n" +
                "\tCommissionYear=2451\n" +
                "\tMaximalSpeed=7.2\n" +
                "\tFirePower=50\n" +
                "\tCrewMembers=1\n" +
                "\tAnnualMaintenanceCost=8500\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\n" +
                "ColonialViper\n" +
                "\tName=Viper2\n" +
                "\tCommissionYear=2451\n" +
                "\tMaximalSpeed=7.2\n" +
                "\tFirePower=50\n" +
                "\tCrewMembers=1\n" +
                "\tAnnualMaintenanceCost=8500\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\n" +
                "StealthCruiser\n" +
                "\tName=USS Galaxy\n" +
                "\tCommissionYear=2370\n" +
                "\tMaximalSpeed=9.0\n" +
                "\tFirePower=50\n" +
                "\tCrewMembers=4\n" +
                "\tAnnualMaintenanceCost=12050\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\n" +
                "CylonRaider\n" +
                "\tName=Raider 1\n" +
                "\tCommissionYear=2056\n" +
                "\tMaximalSpeed=3.5\n" +
                "\tFirePower=50\n" +
                "\tCrewMembers=1\n" +
                "\tAnnualMaintenanceCost=8600\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100], Weapon [name=Evaporator, firePower=30, annualMaintenanceCost=300]]\n" +
                "StealthCruiser\n" +
                "\tName=USS Odyssey\n" +
                "\tCommissionYear=2419\n" +
                "\tMaximalSpeed=9.0\n" +
                "\tFirePower=20\n" +
                "\tCrewMembers=4\n" +
                "\tAnnualMaintenanceCost=11750\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100]]\n" +
                "StealthCruiser\n" +
                "\tName=USS Amsterdamer\n" +
                "\tCommissionYear=2410\n" +
                "\tMaximalSpeed=9.2\n" +
                "\tFirePower=20\n" +
                "\tCrewMembers=3\n" +
                "\tAnnualMaintenanceCost=11950\n" +
                "\tWeaponArray=[Weapon [name=Laser Cannons, firePower=10, annualMaintenanceCost=100]]\n" +
                "TransportShip\n" +
                "\tName=USS Lantree\n" +
                "\tCommissionYear=2457\n" +
                "\tMaximalSpeed=5.1\n" +
                "\tFirePower=10\n" +
                "\tCrewMembers=9\n" +
                "\tAnnualMaintenanceCost=48000\n" +
                "\tCargoCapacity=3000\n" +
                "\tPassengerCapacity=10000\n" +
                "TransportShip\n" +
                "\tName=USS Astral Queen\n" +
                "\tCommissionYear=2396\n" +
                "\tMaximalSpeed=5.1\n" +
                "\tFirePower=10\n" +
                "\tCrewMembers=23\n" +
                "\tAnnualMaintenanceCost=28000\n" +
                "\tCargoCapacity=2000\n" +
                "\tPassengerCapacity=5000\n" +
                "\n" +
                "Ship counts by type:\n" +
                "\t1\tBomber\n" +
                "\t2\tColonialViper\n" +
                "\t1\tCylonRaider\n" +
                "\t1\tFighter\n" +
                "\t3\tStealthCruiser\n" +
                "\t2\tTransportShip\n" +
                "\n" +
                "Weapon types:\n" +
                "\tEvaporator\n" +
                "\tLaser Cannons\n" +
                "\tPhoton Torpedoes\n" +
                "\tQuantum Torpedoes\n" +
                "\tTAU Phasers\n" +
                "\n" +
                "Highest ranking officer per ship:\n" +
                "\tCaptain\tArcher #171\tUSS Amsterdamer\n" +
                "\tCaptain\tArcher #27\tUSS Lantree\n" +
                "\tLieutenant\tCara Thrace\tViper2\n" +
                "\tCaptain\tData #167\tUSS Odyssey\n" +
                "\tCaptain\tFitzRoy #163\tUSS Galaxy\n" +
                "\tCaptain\tLee Adama\tViper1\n" +
                "\tCaptain\tNemo #9\tUSS Astral Queen\n" +
                "\tCaptain\tPicard #194\tUSS Yamaguchi \n" +
                "\tCaptain\tSisko #52\tUSS Defiant\n" +
                "\n" +
                "Officer ranks sorted ascendingly by popularity:\n" +
                "\t7\tLieutenantCommander\n" +
                "\t7\tCommander\n" +
                "\t8\tCaptain\n" +
                "\t15\tLieutenant\n" +
                "\t22\tEnsign\n" +
                "\n" +
                "Fleet Totals:\n" +
                "\tTotal fleet crew members:\t\t\t409\n" +
                "\tAverage age of fleet officers:\t\t\t42.66\n" +
                "\tTotal annual maintenance cost:\t\t\t151525";
        if (i1.equals(i2)) {
            System.out.println("yes");
        }else{
            System.out.println("no");
        }
        }



}
