package de.neonique.controller;

/*Ein Programm um Dateien vom Desktop mit einer vorangeschrieben ID automatisch in Ordner zu sortieren.
Bsp.: _dnd_charsheet.pdf -> D:/Fun/DND/charsheet.pdf
* */

import de.neonique.service.ApplicationService;

//Momentan wird diese Klasse noch verwendet um alle Dependencies zu injecten
//Wird mit Einführung von Dependencies injection Framework geändert
public class FileOrganizer {

    public static void main(String[] args) {

        ApplicationService applicationService = new ApplicationService();
        applicationService.moveAll();

    }
}