<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
    <v-col cols="12" md="12">
      <UiParentCard title="Board">
        <board-edit v-if="isShowEdit"></board-edit>
        <board-list v-else></board-list>
      </UiParentCard>
    </v-col>
  </v-row>
</template>

<style>
a {
  text-decoration: none;
  color: 'black';
}
</style>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import BoardList from '@/components/framework/board/BoardList.vue';

const isShowEdit = ref(false);

const page = ref({ title: 'Board' });
const breadcrumbs = ref([
  {
    title: 'FrameWork',
    disabled: false,
    href: '#'
  },
  {
    title: 'Board',
    disabled: true,
    href: '#'
  }
]);

onMounted(() => {});
</script>
