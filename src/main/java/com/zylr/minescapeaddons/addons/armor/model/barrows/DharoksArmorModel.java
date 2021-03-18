package com.zylr.minescapeaddons.addons.armor.model.barrows;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class DharoksArmorModel extends BipedModel<LivingEntity> {

    public DharoksArmorModel(float modelSize) {
        super(modelSize, 0.0F, 64, 64);

        bipedBody = new ModelRenderer(this);
        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedBody.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);

        ModelRenderer spike3 = new ModelRenderer(this);
        spike3.setRotationPoint(-0.0528F, 10.3097F, -2.8306F);
        bipedBody.addChild(spike3);
        setRotationAngle(spike3, 0.0F, 1.5708F, 0.0F);
        spike3.setTextureOffset(45, 45).addBox(-0.9472F, -0.9847F, -0.8694F, 0.0F, 2.0F, 2.0F, 0.0F, true);
        spike3.setTextureOffset(45, 45).addBox(-0.1972F, -0.2347F, -0.1194F, 0.0F, 0.0F, 0.0F, 0.0F, true);
        spike3.setTextureOffset(45, 45).addBox(-0.4472F, -0.4847F, -0.3694F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike3.setTextureOffset(45, 45).addBox(-0.6972F, -0.7347F, -0.6194F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike3.setTextureOffset(45, 45).addBox(-0.9472F, -0.9847F, -0.8694F, 0.0F, 2.0F, 2.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r1 = new ModelRenderer(this);
        leftArmSpike_r1.setRotationPoint(0.2528F, 0.0153F, 1.3306F);
        spike3.addChild(leftArmSpike_r1);
        setRotationAngle(leftArmSpike_r1, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r1.setTextureOffset(45, 45).addBox(-0.35F, -0.75F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r2 = new ModelRenderer(this);
        leftArmSpike_r2.setRotationPoint(0.5028F, 0.0153F, 1.0806F);
        spike3.addChild(leftArmSpike_r2);
        setRotationAngle(leftArmSpike_r2, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r2.setTextureOffset(45, 45).addBox(-0.35F, -0.5F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r3 = new ModelRenderer(this);
        leftArmSpike_r3.setRotationPoint(0.7528F, 0.0153F, 0.8306F);
        spike3.addChild(leftArmSpike_r3);
        setRotationAngle(leftArmSpike_r3, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r3.setTextureOffset(45, 45).addBox(-0.35F, -0.25F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r4 = new ModelRenderer(this);
        leftArmSpike_r4.setRotationPoint(0.7528F, 0.0153F, 0.3306F);
        spike3.addChild(leftArmSpike_r4);
        setRotationAngle(leftArmSpike_r4, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r4.setTextureOffset(45, 45).addBox(-0.35F, -0.25F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r5 = new ModelRenderer(this);
        leftArmSpike_r5.setRotationPoint(0.5028F, 0.0153F, 0.0806F);
        spike3.addChild(leftArmSpike_r5);
        setRotationAngle(leftArmSpike_r5, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r5.setTextureOffset(45, 45).addBox(-0.35F, -0.5F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r6 = new ModelRenderer(this);
        leftArmSpike_r6.setRotationPoint(0.2528F, 0.0153F, -0.1694F);
        spike3.addChild(leftArmSpike_r6);
        setRotationAngle(leftArmSpike_r6, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r6.setTextureOffset(45, 45).addBox(-0.35F, -0.75F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r7 = new ModelRenderer(this);
        leftArmSpike_r7.setRotationPoint(0.0528F, 0.0153F, 0.1306F);
        spike3.addChild(leftArmSpike_r7);
        setRotationAngle(leftArmSpike_r7, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r7.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r8 = new ModelRenderer(this);
        leftArmSpike_r8.setRotationPoint(0.0528F, -0.4847F, 0.1306F);
        spike3.addChild(leftArmSpike_r8);
        setRotationAngle(leftArmSpike_r8, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r8.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r9 = new ModelRenderer(this);
        leftArmSpike_r9.setRotationPoint(-0.1972F, -0.7347F, 0.1306F);
        spike3.addChild(leftArmSpike_r9);
        setRotationAngle(leftArmSpike_r9, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r9.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r10 = new ModelRenderer(this);
        leftArmSpike_r10.setRotationPoint(-0.1972F, 0.2653F, 0.1306F);
        spike3.addChild(leftArmSpike_r10);
        setRotationAngle(leftArmSpike_r10, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r10.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r11 = new ModelRenderer(this);
        leftArmSpike_r11.setRotationPoint(-0.4472F, 0.5153F, 0.1306F);
        spike3.addChild(leftArmSpike_r11);
        setRotationAngle(leftArmSpike_r11, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r11.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.75F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r12 = new ModelRenderer(this);
        leftArmSpike_r12.setRotationPoint(-0.4472F, -0.9847F, 0.1306F);
        spike3.addChild(leftArmSpike_r12);
        setRotationAngle(leftArmSpike_r12, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r12.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.75F, 0.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r13 = new ModelRenderer(this);
        leftArmSpike_r13.setRotationPoint(-0.0972F, -0.1347F, -0.0444F);
        spike3.addChild(leftArmSpike_r13);
        setRotationAngle(leftArmSpike_r13, 0.0F, -0.7854F, 0.7854F);
        leftArmSpike_r13.setTextureOffset(45, 45).addBox(0.0F, -0.25F, -0.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer spike4 = new ModelRenderer(this);
        spike4.setRotationPoint(-0.0528F, 7.5597F, -2.8306F);
        bipedBody.addChild(spike4);
        setRotationAngle(spike4, 0.0F, 1.5708F, 0.0F);
        spike4.setTextureOffset(45, 45).addBox(-0.9472F, -0.9847F, -0.8694F, 0.0F, 2.0F, 2.0F, 0.0F, true);
        spike4.setTextureOffset(45, 45).addBox(-0.1972F, -0.2347F, -0.1194F, 0.0F, 0.0F, 0.0F, 0.0F, true);
        spike4.setTextureOffset(45, 45).addBox(-0.4472F, -0.4847F, -0.3694F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike4.setTextureOffset(45, 45).addBox(-0.6972F, -0.7347F, -0.6194F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike4.setTextureOffset(45, 45).addBox(-0.9472F, -0.9847F, -0.8694F, 0.0F, 2.0F, 2.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r14 = new ModelRenderer(this);
        leftArmSpike_r14.setRotationPoint(0.2528F, 0.0153F, 1.3306F);
        spike4.addChild(leftArmSpike_r14);
        setRotationAngle(leftArmSpike_r14, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r14.setTextureOffset(45, 45).addBox(-0.35F, -0.75F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r15 = new ModelRenderer(this);
        leftArmSpike_r15.setRotationPoint(0.5028F, 0.0153F, 1.0806F);
        spike4.addChild(leftArmSpike_r15);
        setRotationAngle(leftArmSpike_r15, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r15.setTextureOffset(45, 45).addBox(-0.35F, -0.5F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r16 = new ModelRenderer(this);
        leftArmSpike_r16.setRotationPoint(0.7528F, 0.0153F, 0.8306F);
        spike4.addChild(leftArmSpike_r16);
        setRotationAngle(leftArmSpike_r16, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r16.setTextureOffset(45, 45).addBox(-0.35F, -0.25F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r17 = new ModelRenderer(this);
        leftArmSpike_r17.setRotationPoint(0.7528F, 0.0153F, 0.3306F);
        spike4.addChild(leftArmSpike_r17);
        setRotationAngle(leftArmSpike_r17, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r17.setTextureOffset(45, 45).addBox(-0.35F, -0.25F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r18 = new ModelRenderer(this);
        leftArmSpike_r18.setRotationPoint(0.5028F, 0.0153F, 0.0806F);
        spike4.addChild(leftArmSpike_r18);
        setRotationAngle(leftArmSpike_r18, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r18.setTextureOffset(45, 45).addBox(-0.35F, -0.5F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r19 = new ModelRenderer(this);
        leftArmSpike_r19.setRotationPoint(0.2528F, 0.0153F, -0.1694F);
        spike4.addChild(leftArmSpike_r19);
        setRotationAngle(leftArmSpike_r19, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r19.setTextureOffset(45, 45).addBox(-0.35F, -0.75F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r20 = new ModelRenderer(this);
        leftArmSpike_r20.setRotationPoint(0.0528F, 0.0153F, 0.1306F);
        spike4.addChild(leftArmSpike_r20);
        setRotationAngle(leftArmSpike_r20, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r20.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r21 = new ModelRenderer(this);
        leftArmSpike_r21.setRotationPoint(0.0528F, -0.4847F, 0.1306F);
        spike4.addChild(leftArmSpike_r21);
        setRotationAngle(leftArmSpike_r21, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r21.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r22 = new ModelRenderer(this);
        leftArmSpike_r22.setRotationPoint(-0.1972F, -0.7347F, 0.1306F);
        spike4.addChild(leftArmSpike_r22);
        setRotationAngle(leftArmSpike_r22, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r22.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r23 = new ModelRenderer(this);
        leftArmSpike_r23.setRotationPoint(-0.1972F, 0.2653F, 0.1306F);
        spike4.addChild(leftArmSpike_r23);
        setRotationAngle(leftArmSpike_r23, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r23.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r24 = new ModelRenderer(this);
        leftArmSpike_r24.setRotationPoint(-0.4472F, 0.5153F, 0.1306F);
        spike4.addChild(leftArmSpike_r24);
        setRotationAngle(leftArmSpike_r24, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r24.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.75F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r25 = new ModelRenderer(this);
        leftArmSpike_r25.setRotationPoint(-0.4472F, -0.9847F, 0.1306F);
        spike4.addChild(leftArmSpike_r25);
        setRotationAngle(leftArmSpike_r25, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r25.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.75F, 0.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r26 = new ModelRenderer(this);
        leftArmSpike_r26.setRotationPoint(-0.0972F, -0.1347F, -0.0444F);
        spike4.addChild(leftArmSpike_r26);
        setRotationAngle(leftArmSpike_r26, 0.0F, -0.7854F, 0.7854F);
        leftArmSpike_r26.setTextureOffset(45, 45).addBox(0.0F, -0.25F, -0.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        bipedRightArm = new ModelRenderer(this);
        bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        bipedRightArm.setTextureOffset(24, 0).addBox(-3.0F, 4.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.25F, false);
        bipedRightArm.setTextureOffset(0, 16).addBox(-4.25F, -3.5F, -3.25F, 5.0F, 4.0F, 6.0F, 0.0F, false);

        ModelRenderer spike5 = new ModelRenderer(this);
        spike5.setRotationPoint(4.9472F, 8.3097F, -2.8306F);
        bipedRightArm.addChild(spike5);
        setRotationAngle(spike5, 0.0F, 1.5708F, 0.0F);
        spike5.setTextureOffset(45, 45).addBox(0.1528F, -9.2097F, -7.8694F, 0.0F, 2.0F, 2.0F, 0.0F, true);
        spike5.setTextureOffset(45, 45).addBox(0.9028F, -8.4597F, -7.1194F, 0.0F, 0.0F, 0.0F, 0.0F, true);
        spike5.setTextureOffset(45, 45).addBox(0.6528F, -8.7097F, -7.3694F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike5.setTextureOffset(45, 45).addBox(0.4028F, -8.9597F, -7.6194F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike5.setTextureOffset(45, 45).addBox(0.1528F, -9.2097F, -7.8694F, 0.0F, 2.0F, 2.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r27 = new ModelRenderer(this);
        leftArmSpike_r27.setRotationPoint(0.2528F, 0.0153F, 1.3306F);
        spike5.addChild(leftArmSpike_r27);
        setRotationAngle(leftArmSpike_r27, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r27.setTextureOffset(45, 45).addBox(5.3776F, -8.975F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r28 = new ModelRenderer(this);
        leftArmSpike_r28.setRotationPoint(0.5028F, 0.0153F, 1.0806F);
        spike5.addChild(leftArmSpike_r28);
        setRotationAngle(leftArmSpike_r28, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r28.setTextureOffset(45, 45).addBox(5.3776F, -8.725F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r29 = new ModelRenderer(this);
        leftArmSpike_r29.setRotationPoint(0.7528F, 0.0153F, 0.8306F);
        spike5.addChild(leftArmSpike_r29);
        setRotationAngle(leftArmSpike_r29, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r29.setTextureOffset(45, 45).addBox(5.3776F, -8.475F, -5.1719F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r30 = new ModelRenderer(this);
        leftArmSpike_r30.setRotationPoint(0.7528F, 0.0153F, 0.3306F);
        spike5.addChild(leftArmSpike_r30);
        setRotationAngle(leftArmSpike_r30, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r30.setTextureOffset(45, 45).addBox(5.3776F, -8.475F, -5.1719F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r31 = new ModelRenderer(this);
        leftArmSpike_r31.setRotationPoint(0.5028F, 0.0153F, 0.0806F);
        spike5.addChild(leftArmSpike_r31);
        setRotationAngle(leftArmSpike_r31, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r31.setTextureOffset(45, 45).addBox(5.3776F, -8.725F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r32 = new ModelRenderer(this);
        leftArmSpike_r32.setRotationPoint(0.2528F, 0.0153F, -0.1694F);
        spike5.addChild(leftArmSpike_r32);
        setRotationAngle(leftArmSpike_r32, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r32.setTextureOffset(45, 45).addBox(5.3776F, -8.975F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r33 = new ModelRenderer(this);
        leftArmSpike_r33.setRotationPoint(0.0528F, 0.0153F, 0.1306F);
        spike5.addChild(leftArmSpike_r33);
        setRotationAngle(leftArmSpike_r33, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r33.setTextureOffset(45, 45).addBox(-5.0381F, -6.5938F, -7.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r34 = new ModelRenderer(this);
        leftArmSpike_r34.setRotationPoint(0.0528F, -0.4847F, 0.1306F);
        spike5.addChild(leftArmSpike_r34);
        setRotationAngle(leftArmSpike_r34, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r34.setTextureOffset(45, 45).addBox(-5.0381F, -6.5938F, -7.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r35 = new ModelRenderer(this);
        leftArmSpike_r35.setRotationPoint(-0.1972F, -0.7347F, 0.1306F);
        spike5.addChild(leftArmSpike_r35);
        setRotationAngle(leftArmSpike_r35, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r35.setTextureOffset(45, 45).addBox(-5.0381F, -6.5938F, -7.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r36 = new ModelRenderer(this);
        leftArmSpike_r36.setRotationPoint(-0.1972F, 0.2653F, 0.1306F);
        spike5.addChild(leftArmSpike_r36);
        setRotationAngle(leftArmSpike_r36, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r36.setTextureOffset(45, 45).addBox(-5.0381F, -6.5938F, -7.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r37 = new ModelRenderer(this);
        leftArmSpike_r37.setRotationPoint(-0.4472F, 0.5153F, 0.1306F);
        spike5.addChild(leftArmSpike_r37);
        setRotationAngle(leftArmSpike_r37, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r37.setTextureOffset(45, 45).addBox(-5.0381F, -6.5938F, -7.75F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r38 = new ModelRenderer(this);
        leftArmSpike_r38.setRotationPoint(-0.4472F, -0.9847F, 0.1306F);
        spike5.addChild(leftArmSpike_r38);
        setRotationAngle(leftArmSpike_r38, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r38.setTextureOffset(45, 45).addBox(-5.0381F, -6.5938F, -7.75F, 0.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r39 = new ModelRenderer(this);
        leftArmSpike_r39.setRotationPoint(-0.0972F, -0.1347F, -0.0444F);
        spike5.addChild(leftArmSpike_r39);
        setRotationAngle(leftArmSpike_r39, 0.0F, -0.7854F, 0.7854F);
        leftArmSpike_r39.setTextureOffset(45, 45).addBox(-8.5122F, -6.8438F, -1.6372F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer spike6 = new ModelRenderer(this);
        spike6.setRotationPoint(4.9472F, 8.3097F, -2.8306F);
        bipedRightArm.addChild(spike6);
        setRotationAngle(spike6, 0.0F, 1.5708F, 0.0F);
        spike6.setTextureOffset(45, 45).addBox(0.1528F, -11.6097F, -7.8694F, 0.0F, 2.0F, 2.0F, 0.0F, true);
        spike6.setTextureOffset(45, 45).addBox(0.9028F, -10.8597F, -7.1194F, 0.0F, 0.0F, 0.0F, 0.0F, true);
        spike6.setTextureOffset(45, 45).addBox(0.6528F, -11.1097F, -7.3694F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike6.setTextureOffset(45, 45).addBox(0.4028F, -11.3597F, -7.6194F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike6.setTextureOffset(45, 45).addBox(0.1528F, -11.6097F, -7.8694F, 0.0F, 2.0F, 2.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r40 = new ModelRenderer(this);
        leftArmSpike_r40.setRotationPoint(0.2528F, 0.0153F, 1.3306F);
        spike6.addChild(leftArmSpike_r40);
        setRotationAngle(leftArmSpike_r40, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r40.setTextureOffset(45, 45).addBox(5.3776F, -11.375F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r41 = new ModelRenderer(this);
        leftArmSpike_r41.setRotationPoint(0.5028F, 0.0153F, 1.0806F);
        spike6.addChild(leftArmSpike_r41);
        setRotationAngle(leftArmSpike_r41, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r41.setTextureOffset(45, 45).addBox(5.3776F, -11.125F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r42 = new ModelRenderer(this);
        leftArmSpike_r42.setRotationPoint(0.7528F, 0.0153F, 0.8306F);
        spike6.addChild(leftArmSpike_r42);
        setRotationAngle(leftArmSpike_r42, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r42.setTextureOffset(45, 45).addBox(5.3776F, -10.875F, -5.1719F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r43 = new ModelRenderer(this);
        leftArmSpike_r43.setRotationPoint(0.7528F, 0.0153F, 0.3306F);
        spike6.addChild(leftArmSpike_r43);
        setRotationAngle(leftArmSpike_r43, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r43.setTextureOffset(45, 45).addBox(5.3776F, -10.875F, -5.1719F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r44 = new ModelRenderer(this);
        leftArmSpike_r44.setRotationPoint(0.5028F, 0.0153F, 0.0806F);
        spike6.addChild(leftArmSpike_r44);
        setRotationAngle(leftArmSpike_r44, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r44.setTextureOffset(45, 45).addBox(5.3776F, -11.125F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r45 = new ModelRenderer(this);
        leftArmSpike_r45.setRotationPoint(0.2528F, 0.0153F, -0.1694F);
        spike6.addChild(leftArmSpike_r45);
        setRotationAngle(leftArmSpike_r45, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r45.setTextureOffset(45, 45).addBox(5.3776F, -11.375F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r46 = new ModelRenderer(this);
        leftArmSpike_r46.setRotationPoint(0.0528F, 0.0153F, 0.1306F);
        spike6.addChild(leftArmSpike_r46);
        setRotationAngle(leftArmSpike_r46, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r46.setTextureOffset(45, 45).addBox(-6.7352F, -8.2908F, -7.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r47 = new ModelRenderer(this);
        leftArmSpike_r47.setRotationPoint(0.0528F, -0.4847F, 0.1306F);
        spike6.addChild(leftArmSpike_r47);
        setRotationAngle(leftArmSpike_r47, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r47.setTextureOffset(45, 45).addBox(-6.7352F, -8.2908F, -7.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r48 = new ModelRenderer(this);
        leftArmSpike_r48.setRotationPoint(-0.1972F, -0.7347F, 0.1306F);
        spike6.addChild(leftArmSpike_r48);
        setRotationAngle(leftArmSpike_r48, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r48.setTextureOffset(45, 45).addBox(-6.7352F, -8.2908F, -7.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r49 = new ModelRenderer(this);
        leftArmSpike_r49.setRotationPoint(-0.1972F, 0.2653F, 0.1306F);
        spike6.addChild(leftArmSpike_r49);
        setRotationAngle(leftArmSpike_r49, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r49.setTextureOffset(45, 45).addBox(-6.7352F, -8.2908F, -7.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r50 = new ModelRenderer(this);
        leftArmSpike_r50.setRotationPoint(-0.4472F, 0.5153F, 0.1306F);
        spike6.addChild(leftArmSpike_r50);
        setRotationAngle(leftArmSpike_r50, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r50.setTextureOffset(45, 45).addBox(-6.7352F, -8.2908F, -7.75F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r51 = new ModelRenderer(this);
        leftArmSpike_r51.setRotationPoint(-0.4472F, -0.9847F, 0.1306F);
        spike6.addChild(leftArmSpike_r51);
        setRotationAngle(leftArmSpike_r51, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r51.setTextureOffset(45, 45).addBox(-6.7352F, -8.2908F, -7.75F, 0.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r52 = new ModelRenderer(this);
        leftArmSpike_r52.setRotationPoint(-0.0972F, -0.1347F, -0.0444F);
        spike6.addChild(leftArmSpike_r52);
        setRotationAngle(leftArmSpike_r52, 0.0F, -0.7854F, 0.7854F);
        leftArmSpike_r52.setTextureOffset(45, 45).addBox(-9.7122F, -8.5408F, -0.4372F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer spike7 = new ModelRenderer(this);
        spike7.setRotationPoint(-1.9278F, -4.1653F, -0.5306F);
        bipedRightArm.addChild(spike7);
        setRotationAngle(spike7, 0.0F, 0.0F, -1.5708F);
        spike7.setTextureOffset(45, 45).addBox(-0.9472F, -0.9847F, -0.8694F, 0.0F, 2.0F, 2.0F, 0.0F, true);
        spike7.setTextureOffset(45, 45).addBox(-0.1972F, -0.2347F, -0.1194F, 0.0F, 0.0F, 0.0F, 0.0F, true);
        spike7.setTextureOffset(45, 45).addBox(-0.4472F, -0.4847F, -0.3694F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike7.setTextureOffset(45, 45).addBox(-0.6972F, -0.7347F, -0.6194F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike7.setTextureOffset(45, 45).addBox(-0.9472F, -0.9847F, -0.8694F, 0.0F, 2.0F, 2.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r53 = new ModelRenderer(this);
        leftArmSpike_r53.setRotationPoint(0.2528F, 0.0153F, 1.3306F);
        spike7.addChild(leftArmSpike_r53);
        setRotationAngle(leftArmSpike_r53, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r53.setTextureOffset(45, 45).addBox(-0.35F, -0.75F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r54 = new ModelRenderer(this);
        leftArmSpike_r54.setRotationPoint(0.5028F, 0.0153F, 1.0806F);
        spike7.addChild(leftArmSpike_r54);
        setRotationAngle(leftArmSpike_r54, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r54.setTextureOffset(45, 45).addBox(-0.35F, -0.5F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r55 = new ModelRenderer(this);
        leftArmSpike_r55.setRotationPoint(0.7528F, 0.0153F, 0.8306F);
        spike7.addChild(leftArmSpike_r55);
        setRotationAngle(leftArmSpike_r55, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r55.setTextureOffset(45, 45).addBox(-0.35F, -0.25F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r56 = new ModelRenderer(this);
        leftArmSpike_r56.setRotationPoint(0.7528F, 0.0153F, 0.3306F);
        spike7.addChild(leftArmSpike_r56);
        setRotationAngle(leftArmSpike_r56, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r56.setTextureOffset(45, 45).addBox(-0.35F, -0.25F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r57 = new ModelRenderer(this);
        leftArmSpike_r57.setRotationPoint(0.5028F, 0.0153F, 0.0806F);
        spike7.addChild(leftArmSpike_r57);
        setRotationAngle(leftArmSpike_r57, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r57.setTextureOffset(45, 45).addBox(-0.35F, -0.5F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r58 = new ModelRenderer(this);
        leftArmSpike_r58.setRotationPoint(0.2528F, 0.0153F, -0.1694F);
        spike7.addChild(leftArmSpike_r58);
        setRotationAngle(leftArmSpike_r58, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r58.setTextureOffset(45, 45).addBox(-0.35F, -0.75F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r59 = new ModelRenderer(this);
        leftArmSpike_r59.setRotationPoint(0.0528F, 0.0153F, 0.1306F);
        spike7.addChild(leftArmSpike_r59);
        setRotationAngle(leftArmSpike_r59, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r59.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r60 = new ModelRenderer(this);
        leftArmSpike_r60.setRotationPoint(0.0528F, -0.4847F, 0.1306F);
        spike7.addChild(leftArmSpike_r60);
        setRotationAngle(leftArmSpike_r60, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r60.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r61 = new ModelRenderer(this);
        leftArmSpike_r61.setRotationPoint(-0.1972F, -0.7347F, 0.1306F);
        spike7.addChild(leftArmSpike_r61);
        setRotationAngle(leftArmSpike_r61, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r61.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r62 = new ModelRenderer(this);
        leftArmSpike_r62.setRotationPoint(-0.1972F, 0.2653F, 0.1306F);
        spike7.addChild(leftArmSpike_r62);
        setRotationAngle(leftArmSpike_r62, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r62.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r63 = new ModelRenderer(this);
        leftArmSpike_r63.setRotationPoint(-0.4472F, 0.5153F, 0.1306F);
        spike7.addChild(leftArmSpike_r63);
        setRotationAngle(leftArmSpike_r63, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r63.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.75F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r64 = new ModelRenderer(this);
        leftArmSpike_r64.setRotationPoint(-0.4472F, -0.9847F, 0.1306F);
        spike7.addChild(leftArmSpike_r64);
        setRotationAngle(leftArmSpike_r64, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r64.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.75F, 0.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r65 = new ModelRenderer(this);
        leftArmSpike_r65.setRotationPoint(-0.0972F, -0.1347F, -0.0444F);
        spike7.addChild(leftArmSpike_r65);
        setRotationAngle(leftArmSpike_r65, 0.0F, -0.7854F, 0.7854F);
        leftArmSpike_r65.setTextureOffset(45, 45).addBox(0.0F, -0.25F, -0.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer spike8 = new ModelRenderer(this);
        spike8.setRotationPoint(-8.8028F, 8.3097F, 2.6694F);
        bipedRightArm.addChild(spike8);
        setRotationAngle(spike8, 0.0F, -1.5708F, 0.0F);
        spike8.setTextureOffset(45, 45).addBox(0.1528F, -11.6097F, -7.8694F, 0.0F, 2.0F, 2.0F, 0.0F, true);
        spike8.setTextureOffset(45, 45).addBox(0.9028F, -10.8597F, -7.1194F, 0.0F, 0.0F, 0.0F, 0.0F, true);
        spike8.setTextureOffset(45, 45).addBox(0.6528F, -11.1097F, -7.3694F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike8.setTextureOffset(45, 45).addBox(0.4028F, -11.3597F, -7.6194F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike8.setTextureOffset(45, 45).addBox(0.1528F, -11.6097F, -7.8694F, 0.0F, 2.0F, 2.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r66 = new ModelRenderer(this);
        leftArmSpike_r66.setRotationPoint(0.2528F, 0.0153F, 1.3306F);
        spike8.addChild(leftArmSpike_r66);
        setRotationAngle(leftArmSpike_r66, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r66.setTextureOffset(45, 45).addBox(5.3776F, -11.375F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r67 = new ModelRenderer(this);
        leftArmSpike_r67.setRotationPoint(0.5028F, 0.0153F, 1.0806F);
        spike8.addChild(leftArmSpike_r67);
        setRotationAngle(leftArmSpike_r67, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r67.setTextureOffset(45, 45).addBox(5.3776F, -11.125F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r68 = new ModelRenderer(this);
        leftArmSpike_r68.setRotationPoint(0.7528F, 0.0153F, 0.8306F);
        spike8.addChild(leftArmSpike_r68);
        setRotationAngle(leftArmSpike_r68, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r68.setTextureOffset(45, 45).addBox(5.3776F, -10.875F, -5.1719F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r69 = new ModelRenderer(this);
        leftArmSpike_r69.setRotationPoint(0.7528F, 0.0153F, 0.3306F);
        spike8.addChild(leftArmSpike_r69);
        setRotationAngle(leftArmSpike_r69, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r69.setTextureOffset(45, 45).addBox(5.3776F, -10.875F, -5.1719F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r70 = new ModelRenderer(this);
        leftArmSpike_r70.setRotationPoint(0.5028F, 0.0153F, 0.0806F);
        spike8.addChild(leftArmSpike_r70);
        setRotationAngle(leftArmSpike_r70, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r70.setTextureOffset(45, 45).addBox(5.3776F, -11.125F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r71 = new ModelRenderer(this);
        leftArmSpike_r71.setRotationPoint(0.2528F, 0.0153F, -0.1694F);
        spike8.addChild(leftArmSpike_r71);
        setRotationAngle(leftArmSpike_r71, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r71.setTextureOffset(45, 45).addBox(5.3776F, -11.375F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r72 = new ModelRenderer(this);
        leftArmSpike_r72.setRotationPoint(0.0528F, 0.0153F, 0.1306F);
        spike8.addChild(leftArmSpike_r72);
        setRotationAngle(leftArmSpike_r72, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r72.setTextureOffset(45, 45).addBox(-6.7352F, -8.2908F, -7.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r73 = new ModelRenderer(this);
        leftArmSpike_r73.setRotationPoint(0.0528F, -0.4847F, 0.1306F);
        spike8.addChild(leftArmSpike_r73);
        setRotationAngle(leftArmSpike_r73, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r73.setTextureOffset(45, 45).addBox(-6.7352F, -8.2908F, -7.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r74 = new ModelRenderer(this);
        leftArmSpike_r74.setRotationPoint(-0.1972F, -0.7347F, 0.1306F);
        spike8.addChild(leftArmSpike_r74);
        setRotationAngle(leftArmSpike_r74, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r74.setTextureOffset(45, 45).addBox(-6.7352F, -8.2908F, -7.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r75 = new ModelRenderer(this);
        leftArmSpike_r75.setRotationPoint(-0.1972F, 0.2653F, 0.1306F);
        spike8.addChild(leftArmSpike_r75);
        setRotationAngle(leftArmSpike_r75, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r75.setTextureOffset(45, 45).addBox(-6.7352F, -8.2908F, -7.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r76 = new ModelRenderer(this);
        leftArmSpike_r76.setRotationPoint(-0.4472F, 0.5153F, 0.1306F);
        spike8.addChild(leftArmSpike_r76);
        setRotationAngle(leftArmSpike_r76, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r76.setTextureOffset(45, 45).addBox(-6.7352F, -8.2908F, -7.75F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r77 = new ModelRenderer(this);
        leftArmSpike_r77.setRotationPoint(-0.4472F, -0.9847F, 0.1306F);
        spike8.addChild(leftArmSpike_r77);
        setRotationAngle(leftArmSpike_r77, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r77.setTextureOffset(45, 45).addBox(-6.7352F, -8.2908F, -7.75F, 0.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r78 = new ModelRenderer(this);
        leftArmSpike_r78.setRotationPoint(-0.0972F, -0.1347F, -0.0444F);
        spike8.addChild(leftArmSpike_r78);
        setRotationAngle(leftArmSpike_r78, 0.0F, -0.7854F, 0.7854F);
        leftArmSpike_r78.setTextureOffset(45, 45).addBox(-9.7122F, -8.5408F, -0.4372F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer spike9 = new ModelRenderer(this);
        spike9.setRotationPoint(-8.8028F, 8.3097F, 2.6694F);
        bipedRightArm.addChild(spike9);
        setRotationAngle(spike9, 0.0F, -1.5708F, 0.0F);
        spike9.setTextureOffset(45, 45).addBox(0.1528F, -9.2847F, -7.8694F, 0.0F, 2.0F, 2.0F, 0.0F, true);
        spike9.setTextureOffset(45, 45).addBox(0.9028F, -8.5347F, -7.1194F, 0.0F, 0.0F, 0.0F, 0.0F, true);
        spike9.setTextureOffset(45, 45).addBox(0.6528F, -8.7847F, -7.3694F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike9.setTextureOffset(45, 45).addBox(0.4028F, -9.0347F, -7.6194F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike9.setTextureOffset(45, 45).addBox(0.1528F, -9.2847F, -7.8694F, 0.0F, 2.0F, 2.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r79 = new ModelRenderer(this);
        leftArmSpike_r79.setRotationPoint(0.2528F, 0.0153F, 1.3306F);
        spike9.addChild(leftArmSpike_r79);
        setRotationAngle(leftArmSpike_r79, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r79.setTextureOffset(45, 45).addBox(5.3776F, -9.05F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r80 = new ModelRenderer(this);
        leftArmSpike_r80.setRotationPoint(0.5028F, 0.0153F, 1.0806F);
        spike9.addChild(leftArmSpike_r80);
        setRotationAngle(leftArmSpike_r80, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r80.setTextureOffset(45, 45).addBox(5.3776F, -8.8F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r81 = new ModelRenderer(this);
        leftArmSpike_r81.setRotationPoint(0.7528F, 0.0153F, 0.8306F);
        spike9.addChild(leftArmSpike_r81);
        setRotationAngle(leftArmSpike_r81, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r81.setTextureOffset(45, 45).addBox(5.3776F, -8.55F, -5.1719F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r82 = new ModelRenderer(this);
        leftArmSpike_r82.setRotationPoint(0.7528F, 0.0153F, 0.3306F);
        spike9.addChild(leftArmSpike_r82);
        setRotationAngle(leftArmSpike_r82, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r82.setTextureOffset(45, 45).addBox(5.3776F, -8.55F, -5.1719F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r83 = new ModelRenderer(this);
        leftArmSpike_r83.setRotationPoint(0.5028F, 0.0153F, 0.0806F);
        spike9.addChild(leftArmSpike_r83);
        setRotationAngle(leftArmSpike_r83, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r83.setTextureOffset(45, 45).addBox(5.3776F, -8.8F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r84 = new ModelRenderer(this);
        leftArmSpike_r84.setRotationPoint(0.2528F, 0.0153F, -0.1694F);
        spike9.addChild(leftArmSpike_r84);
        setRotationAngle(leftArmSpike_r84, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r84.setTextureOffset(45, 45).addBox(5.3776F, -9.05F, -5.1719F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r85 = new ModelRenderer(this);
        leftArmSpike_r85.setRotationPoint(0.0528F, 0.0153F, 0.1306F);
        spike9.addChild(leftArmSpike_r85);
        setRotationAngle(leftArmSpike_r85, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r85.setTextureOffset(45, 45).addBox(-5.0912F, -6.6468F, -7.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r86 = new ModelRenderer(this);
        leftArmSpike_r86.setRotationPoint(0.0528F, -0.4847F, 0.1306F);
        spike9.addChild(leftArmSpike_r86);
        setRotationAngle(leftArmSpike_r86, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r86.setTextureOffset(45, 45).addBox(-5.0912F, -6.6468F, -7.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r87 = new ModelRenderer(this);
        leftArmSpike_r87.setRotationPoint(-0.1972F, -0.7347F, 0.1306F);
        spike9.addChild(leftArmSpike_r87);
        setRotationAngle(leftArmSpike_r87, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r87.setTextureOffset(45, 45).addBox(-5.0912F, -6.6468F, -7.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r88 = new ModelRenderer(this);
        leftArmSpike_r88.setRotationPoint(-0.1972F, 0.2653F, 0.1306F);
        spike9.addChild(leftArmSpike_r88);
        setRotationAngle(leftArmSpike_r88, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r88.setTextureOffset(45, 45).addBox(-5.0912F, -6.6468F, -7.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r89 = new ModelRenderer(this);
        leftArmSpike_r89.setRotationPoint(-0.4472F, 0.5153F, 0.1306F);
        spike9.addChild(leftArmSpike_r89);
        setRotationAngle(leftArmSpike_r89, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r89.setTextureOffset(45, 45).addBox(-5.0912F, -6.6468F, -7.75F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r90 = new ModelRenderer(this);
        leftArmSpike_r90.setRotationPoint(-0.4472F, -0.9847F, 0.1306F);
        spike9.addChild(leftArmSpike_r90);
        setRotationAngle(leftArmSpike_r90, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r90.setTextureOffset(45, 45).addBox(-5.0912F, -6.6468F, -7.75F, 0.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r91 = new ModelRenderer(this);
        leftArmSpike_r91.setRotationPoint(-0.0972F, -0.1347F, -0.0444F);
        spike9.addChild(leftArmSpike_r91);
        setRotationAngle(leftArmSpike_r91, 0.0F, -0.7854F, 0.7854F);
        leftArmSpike_r91.setTextureOffset(45, 45).addBox(-8.5497F, -6.8968F, -1.5997F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        bipedLeftArm = new ModelRenderer(this);
        bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        bipedLeftArm.setTextureOffset(24, 0).addBox(-1.0F, 4.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.25F, false);
        bipedLeftArm.setTextureOffset(40, 0).addBox(-1.25F, -2.5F, -2.25F, 4.0F, 3.0F, 4.0F, 0.0F, false);

        ModelRenderer spike = new ModelRenderer(this);
        spike.setRotationPoint(-0.275F, 0.0F, -0.225F);
        bipedLeftArm.addChild(spike);
        spike.setTextureOffset(45, 45).addBox(3.0F, 5.325F, -1.1F, 0.0F, 2.0F, 2.0F, 0.0F, true);
        spike.setTextureOffset(45, 45).addBox(3.75F, 6.075F, -0.35F, 0.0F, 0.0F, 0.0F, 0.0F, true);
        spike.setTextureOffset(45, 45).addBox(3.5F, 5.825F, -0.6F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike.setTextureOffset(45, 45).addBox(3.25F, 5.575F, -0.85F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike.setTextureOffset(45, 45).addBox(3.0F, 5.325F, -1.1F, 0.0F, 2.0F, 2.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r92 = new ModelRenderer(this);
        leftArmSpike_r92.setRotationPoint(4.2F, 6.325F, 1.1F);
        spike.addChild(leftArmSpike_r92);
        setRotationAngle(leftArmSpike_r92, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r92.setTextureOffset(45, 45).addBox(-0.35F, -0.75F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r93 = new ModelRenderer(this);
        leftArmSpike_r93.setRotationPoint(4.45F, 6.325F, 0.85F);
        spike.addChild(leftArmSpike_r93);
        setRotationAngle(leftArmSpike_r93, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r93.setTextureOffset(45, 45).addBox(-0.35F, -0.5F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r94 = new ModelRenderer(this);
        leftArmSpike_r94.setRotationPoint(4.7F, 6.325F, 0.6F);
        spike.addChild(leftArmSpike_r94);
        setRotationAngle(leftArmSpike_r94, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r94.setTextureOffset(45, 45).addBox(-0.35F, -0.25F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r95 = new ModelRenderer(this);
        leftArmSpike_r95.setRotationPoint(4.7F, 6.325F, 0.1F);
        spike.addChild(leftArmSpike_r95);
        setRotationAngle(leftArmSpike_r95, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r95.setTextureOffset(45, 45).addBox(-0.35F, -0.25F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r96 = new ModelRenderer(this);
        leftArmSpike_r96.setRotationPoint(4.45F, 6.325F, -0.15F);
        spike.addChild(leftArmSpike_r96);
        setRotationAngle(leftArmSpike_r96, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r96.setTextureOffset(45, 45).addBox(-0.35F, -0.5F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r97 = new ModelRenderer(this);
        leftArmSpike_r97.setRotationPoint(4.2F, 6.325F, -0.4F);
        spike.addChild(leftArmSpike_r97);
        setRotationAngle(leftArmSpike_r97, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r97.setTextureOffset(45, 45).addBox(-0.35F, -0.75F, -1.0F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r98 = new ModelRenderer(this);
        leftArmSpike_r98.setRotationPoint(4.0F, 6.325F, -0.1F);
        spike.addChild(leftArmSpike_r98);
        setRotationAngle(leftArmSpike_r98, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r98.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r99 = new ModelRenderer(this);
        leftArmSpike_r99.setRotationPoint(4.0F, 5.825F, -0.1F);
        spike.addChild(leftArmSpike_r99);
        setRotationAngle(leftArmSpike_r99, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r99.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r100 = new ModelRenderer(this);
        leftArmSpike_r100.setRotationPoint(3.75F, 5.575F, -0.1F);
        spike.addChild(leftArmSpike_r100);
        setRotationAngle(leftArmSpike_r100, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r100.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r101 = new ModelRenderer(this);
        leftArmSpike_r101.setRotationPoint(3.75F, 6.575F, -0.1F);
        spike.addChild(leftArmSpike_r101);
        setRotationAngle(leftArmSpike_r101, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r101.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r102 = new ModelRenderer(this);
        leftArmSpike_r102.setRotationPoint(3.5F, 6.825F, -0.1F);
        spike.addChild(leftArmSpike_r102);
        setRotationAngle(leftArmSpike_r102, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r102.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.75F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r103 = new ModelRenderer(this);
        leftArmSpike_r103.setRotationPoint(3.5F, 5.325F, -0.1F);
        spike.addChild(leftArmSpike_r103);
        setRotationAngle(leftArmSpike_r103, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r103.setTextureOffset(45, 45).addBox(0.0F, 0.0F, -0.75F, 0.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r104 = new ModelRenderer(this);
        leftArmSpike_r104.setRotationPoint(3.85F, 6.175F, -0.275F);
        spike.addChild(leftArmSpike_r104);
        setRotationAngle(leftArmSpike_r104, 0.0F, -0.7854F, 0.7854F);
        leftArmSpike_r104.setTextureOffset(45, 45).addBox(0.0F, -0.25F, -0.25F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer spike2 = new ModelRenderer(this);
        spike2.setRotationPoint(3.9472F, 6.1847F, -0.0556F);
        bipedLeftArm.addChild(spike2);
        setRotationAngle(spike2, 0.6545F, 0.0F, 0.0F);
        spike2.setTextureOffset(45, 45).addBox(-0.9472F, -1.4289F, -0.3395F, 0.0F, 2.0F, 2.0F, 0.0F, true);
        spike2.setTextureOffset(45, 45).addBox(-0.1972F, -0.6789F, 0.4105F, 0.0F, 0.0F, 0.0F, 0.0F, true);
        spike2.setTextureOffset(45, 45).addBox(-0.4472F, -0.9289F, 0.1605F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike2.setTextureOffset(45, 45).addBox(-0.6972F, -1.1789F, -0.0895F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        spike2.setTextureOffset(45, 45).addBox(-0.9472F, -1.4289F, -0.3395F, 0.0F, 2.0F, 2.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r105 = new ModelRenderer(this);
        leftArmSpike_r105.setRotationPoint(0.2528F, 0.0153F, 1.3306F);
        spike2.addChild(leftArmSpike_r105);
        setRotationAngle(leftArmSpike_r105, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r105.setTextureOffset(45, 45).addBox(-0.7247F, -1.1942F, -0.6253F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r106 = new ModelRenderer(this);
        leftArmSpike_r106.setRotationPoint(0.5028F, 0.0153F, 1.0806F);
        spike2.addChild(leftArmSpike_r106);
        setRotationAngle(leftArmSpike_r106, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r106.setTextureOffset(45, 45).addBox(-0.7247F, -0.9442F, -0.6253F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r107 = new ModelRenderer(this);
        leftArmSpike_r107.setRotationPoint(0.7528F, 0.0153F, 0.8306F);
        spike2.addChild(leftArmSpike_r107);
        setRotationAngle(leftArmSpike_r107, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r107.setTextureOffset(45, 45).addBox(-0.7247F, -0.6942F, -0.6253F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r108 = new ModelRenderer(this);
        leftArmSpike_r108.setRotationPoint(0.7528F, 0.0153F, 0.3306F);
        spike2.addChild(leftArmSpike_r108);
        setRotationAngle(leftArmSpike_r108, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r108.setTextureOffset(45, 45).addBox(-0.7247F, -0.6942F, -0.6253F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r109 = new ModelRenderer(this);
        leftArmSpike_r109.setRotationPoint(0.5028F, 0.0153F, 0.0806F);
        spike2.addChild(leftArmSpike_r109);
        setRotationAngle(leftArmSpike_r109, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r109.setTextureOffset(45, 45).addBox(-0.7247F, -0.9442F, -0.6253F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r110 = new ModelRenderer(this);
        leftArmSpike_r110.setRotationPoint(0.2528F, 0.0153F, -0.1694F);
        spike2.addChild(leftArmSpike_r110);
        setRotationAngle(leftArmSpike_r110, 0.0F, 0.7854F, 0.0F);
        leftArmSpike_r110.setTextureOffset(45, 45).addBox(-0.7247F, -1.1942F, -0.6253F, 0.0F, 1.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r111 = new ModelRenderer(this);
        leftArmSpike_r111.setRotationPoint(0.0528F, 0.0153F, 0.1306F);
        spike2.addChild(leftArmSpike_r111);
        setRotationAngle(leftArmSpike_r111, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r111.setTextureOffset(45, 45).addBox(-0.3141F, -0.3141F, 0.2799F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r112 = new ModelRenderer(this);
        leftArmSpike_r112.setRotationPoint(0.0528F, -0.4847F, 0.1306F);
        spike2.addChild(leftArmSpike_r112);
        setRotationAngle(leftArmSpike_r112, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r112.setTextureOffset(45, 45).addBox(-0.3141F, -0.3141F, 0.2799F, 0.0F, 0.0F, 0.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r113 = new ModelRenderer(this);
        leftArmSpike_r113.setRotationPoint(-0.1972F, -0.7347F, 0.1306F);
        spike2.addChild(leftArmSpike_r113);
        setRotationAngle(leftArmSpike_r113, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r113.setTextureOffset(45, 45).addBox(-0.3141F, -0.3141F, 0.0299F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r114 = new ModelRenderer(this);
        leftArmSpike_r114.setRotationPoint(-0.1972F, 0.2653F, 0.1306F);
        spike2.addChild(leftArmSpike_r114);
        setRotationAngle(leftArmSpike_r114, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r114.setTextureOffset(45, 45).addBox(-0.3141F, -0.3141F, 0.0299F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r115 = new ModelRenderer(this);
        leftArmSpike_r115.setRotationPoint(-0.4472F, 0.5153F, 0.1306F);
        spike2.addChild(leftArmSpike_r115);
        setRotationAngle(leftArmSpike_r115, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r115.setTextureOffset(45, 45).addBox(-0.3141F, -0.3141F, -0.2201F, 0.0F, 0.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r116 = new ModelRenderer(this);
        leftArmSpike_r116.setRotationPoint(-0.4472F, -0.9847F, 0.1306F);
        spike2.addChild(leftArmSpike_r116);
        setRotationAngle(leftArmSpike_r116, 0.0F, 0.0F, 0.7854F);
        leftArmSpike_r116.setTextureOffset(45, 45).addBox(-0.3141F, -0.3141F, -0.2201F, 0.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer leftArmSpike_r117 = new ModelRenderer(this);
        leftArmSpike_r117.setRotationPoint(-0.0972F, -0.1347F, -0.0444F);
        spike2.addChild(leftArmSpike_r117);
        setRotationAngle(leftArmSpike_r117, 0.0F, -0.7854F, 0.7854F);
        leftArmSpike_r117.setTextureOffset(45, 45).addBox(0.1526F, -0.5641F, 0.3468F, 0.0F, 0.0F, 0.0F, 0.0F, true);

    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}