package com.Nxer.TwistSpaceTechnology.system.DysonSphereProgram.logic;

import static com.Nxer.TwistSpaceTechnology.system.DysonSphereProgram.logic.DSP_Values.solarSailPowerPoint;
import static com.Nxer.TwistSpaceTechnology.system.DysonSphereProgram.logic.DSP_WorldSavedData.markDataDirty;

import java.io.Serializable;

import com.Nxer.TwistSpaceTechnology.TwistSpaceTechnology;

// spotless:off
public class DSP_DataCell implements Serializable {

    // region Variables
    private String ownerName;
    private DSP_Galaxy galaxy;
    private long amountDSPSolarSail;
    private long amountDSPNode;
    private boolean dirty;
    private long maxDSPPowerPoint;
    private long usedDSPPowerPoint;
    
    // endregion

    // region Class Constructor
    public DSP_DataCell(String ownerName, DSP_Galaxy galaxy) {
        this.ownerName = ownerName;
        this.galaxy = galaxy;
        this.amountDSPSolarSail = 0;
        this.amountDSPNode = 0;
        this.dirty = true;
        this.maxDSPPowerPoint = 0;
        this.usedDSPPowerPoint = 0;
    }

    public DSP_DataCell() {
        this.ownerName = "defaultPlayerWithErrorInformation";
        this.galaxy = DSP_Galaxy.DSP_Galaxy_NULL;
        this.amountDSPNode = 0;
        this.amountDSPSolarSail = 0;
        this.dirty = true;
        this.maxDSPPowerPoint = 0;
        this.usedDSPPowerPoint = 0;
    }

    // endregion

    // region Methods
    
    /**
     * @return Remaining Available DSP Power Points
     */
    public long getDSPPowerPointCanUse(){
        return this.getMaxDSPPowerPoint() - this.usedDSPPowerPoint;
    }
    
    /**
     * Try to decrease the DSP Power Points used part amount.
     * @param amount    The <b style:"color=#00FF00">amount</b>.
     * @return          True means ok; <p>False means something was error.
     */
    public boolean tryDecreaseUsedPowerPoint(long amount){
        if (this.usedDSPPowerPoint >= amount){
            this.usedDSPPowerPoint -= amount;
            markDataDirty();
            return true;
        }
        TwistSpaceTechnology.LOG.info("Error ! Trying decrease an amount larger than used DSP Power Point !");
        TwistSpaceTechnology.LOG.info("Trying amount: "+amount+" ; Used point: "+this.usedDSPPowerPoint+" ;");
        return false;
    }
    
    /**
     * Try to request using DSP Power Points.
     *
     * @param amount    The points request.
     * @return          True means success; <p>False means nothing happened, request failed.</p>
     */
    public boolean tryUsePowerPoint(long amount){
        if (this.canUsePowerPoint(amount)){
            this.usedDSPPowerPoint += amount;
            markDataDirty();
            return true;
        }
        return false;
    }
    
    /**
     *
     * @param amount    The trying amount.
     * @return          False means don't do.
     */
    public boolean canUsePowerPoint(long amount){
        return this.usedDSPPowerPoint + amount <= this.getMaxDSPPowerPoint();
    }
    
    @SuppressWarnings("unsafe")
    public boolean decreaseUsedPowerPointUnsafely(long amount){
        markDataDirty();
        if (tryDecreaseUsedPowerPoint(amount)){
            return true;
        }
        this.markDirty().usedDSPPowerPoint -= amount;
        return false;
    }
    
    @SuppressWarnings("unsafe")
    public DSP_DataCell setUsedPowerPointUnsafely(long amount){
        this.markDirty().usedDSPPowerPoint = amount;
        markDataDirty();
        TwistSpaceTechnology.LOG.info("Set 0 to UsedPowerPoint Unsafely at: "+this);
        return this;
    }
    
    public DSP_DataCell flushMaxDSPPowerPoint(){
        this.cancelDirty()
            .setMaxDSPPowerPoint(
                (long) (
                    solarSailPowerPoint
                        * this.amountDSPSolarSail
                        * Math.pow(this.amountDSPNode + 1, 0.5)));
        return this;
    }
    
    public long getMaxDSPPowerPoint() {
        if (this.dirty){
            return this.flushMaxDSPPowerPoint().maxDSPPowerPoint;
        }
        return this.maxDSPPowerPoint;
    }

    public DSP_DataCell addDSPSolarSail(long amount) {
        this.amountDSPSolarSail += amount;
        this.flushMaxDSPPowerPoint();
        markDataDirty();
        return this;
    }

    public DSP_DataCell addDSPNode(long amount) {
        this.amountDSPNode += amount;
        this.flushMaxDSPPowerPoint();
        markDataDirty();
        return this;
    }
    
    public DSP_DataCell setMaxDSPPowerPoint(long amount){
        this.maxDSPPowerPoint = amount;
        markDataDirty();
        return this;
    }
    
    public DSP_DataCell markDirty(){
        this.dirty = true;
        return this;
    }
    
    public DSP_DataCell cancelDirty(){
        this.dirty = false;
        return this;
    }

    @Override
    public String toString() {
        return "DSP_DataCell{ ownerName:" + ownerName
                   + " , galaxy:"
                   + galaxy
                   + " , amountDSPSolarSail:"
                   + amountDSPSolarSail
                   + " , amountDSPNode:"
                   + amountDSPNode
                   + " , maxDSPPowerPoint:"
                   + maxDSPPowerPoint
                   + " , usedDSPPowerPoint:"
                   + usedDSPPowerPoint
                   + " , PowerPointCanUse: "
                   + getDSPPowerPointCanUse()
                   + " }";
    }

    // endregion

    // region Getters and Setters

    public String getOwnerName() {
        return ownerName;
    }

    public DSP_DataCell setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        markDataDirty();
        return this;
    }

    public DSP_Galaxy getGalaxy() {
        return galaxy;
    }

    public DSP_DataCell setGalaxy(DSP_Galaxy galaxy) {
        this.galaxy = galaxy;
        markDataDirty();
        return this;
    }

    public long getDSPSolarSail() {
        return amountDSPSolarSail;
    }

    public DSP_DataCell setDSPSolarSail(long amountDSPSolarSail) {
        this.amountDSPSolarSail = amountDSPSolarSail;
        this.flushMaxDSPPowerPoint();
        markDataDirty();
        return this;
    }

    public long getDSPNode() {
        return amountDSPNode;
    }

    public DSP_DataCell setDSPNode(long amountDSPNode) {
        this.amountDSPNode = amountDSPNode;
        this.flushMaxDSPPowerPoint();
        markDataDirty();
        return this;
    }

    // endregion

    // spotless:on
}
