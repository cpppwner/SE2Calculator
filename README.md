# SE2Calculator

## Introduction
This application is for academic demo purposes only, namely the
source "Software Engineering II" @AAU.
The requirements specify that it shall be possible to calculate a division.

## Implementation
The application implements the Passive View (aka. Model-View-Presenter), but
since there is no Model class, i'd prefer Fowler's name.

## Hints
The Divide button is deactivated as long as dividend and divisor are not 
specified, but will be activated as soon as both values are set.
The output fields are hidden at the beginning, but are filled, after the 
Divide button was pressed.

NOTE: Since division by zero is not allowed from mathematical point of view,
an appropriate error message is displayed.